package sistema;


import entidades.ImgComparacao;
import entidades.Post;
import flask.EnviaRequisicaoFlask;
import instaloader.RequisicaoInstaloader;
import posts.Descricao;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class InstagramGraffitiDetector {

	private RequisicaoInstaloader requisicaoInstaloader;
    private EnviaRequisicaoFlask enviaRequisicaoFlask;
    private ImgComparacao imgComparacao;
	private List<Post> postList;
    private String path;
    private Boolean status;


	public InstagramGraffitiDetector(){
    	this.requisicaoInstaloader = new RequisicaoInstaloader();
    	this.enviaRequisicaoFlask = new EnviaRequisicaoFlask();
    	this.imgComparacao = new ImgComparacao();
    	this.postList = new ArrayList<>();
    	this.status = false;
    }

    public List<Post> detector() throws IOException {

        Scanner scr = new Scanner(System.in);
        Descricao descricao = new Descricao();      
        Integer op;
        String porcentagens_siamese_model;

       // requisicaoInstaloader.definindoUsuarioSistema();

       // do{
            requisicaoInstaloader.definindoRequisicaoInstaloader();
            this.setPath("C:\\ProjetosJupyterNotebooks\\data\\instaloaderData\\" + requisicaoInstaloader.getNomeDir() + "\\imgs");
            postList = enviaRequisicaoFlask.predictImagem(this.getPath());
            if (postList.isEmpty())
                System.out.println("N„o h· posts");
            else{
                descricao.defineDescricao(postList,path);                
                postList = filtragemPosts(postList);
                if(postList.isEmpty())
                    System.out.println("N„o h· posts que satisfazem as condicoes");
                else{
                	postList= tratamentoDescricao(postList);
                    System.out.println(postList.toString());
                    System.out.println();  
                    this.setStatus(true);
                }
            }
            
            return postList;	

         //   System.out.println("Deseja fazer outra requisi√ß√£o: 1-Sim ou 2-Nao");
         //   op = scr.nextInt();

       // }while(op != 2);

       // System.out.println("Finalizando...");
    }
    
    public List<Post> siameseModelPredict(String pathImgUsuario) throws IOException{
        Integer op;
        String porcentagens_siamese_model;
        Scanner scr = new Scanner(System.in);
    	
    	tratamento_imgs(this.getPostList(), this.getPath());
        imgComparacao.setPath("C:\\img_predict\\img_usuario\\img_usuario.jpeg"); //scr.nextLine() TODO
        porcentagens_siamese_model=enviaRequisicaoFlask.predictSiamese(imgComparacao.getPath());
        this.setPostList(submetendoPorcentagemSimilaridade(postList, porcentagens_siamese_model));
        System.out.println(postList.toString());
        System.out.println(porcentagens_siamese_model);
        return postList;
    }

    public List<Post> tratamentoDescricao(List<Post> postList){
    	String descricao;
    	Integer size;
    	List<Post> posts = postList;
    	
    	 for (Post post : posts) {
    		 descricao = post.getDescricao();
    		 if(descricao.contains("null")) {
    			 size = descricao.length();
    			 descricao = descricao.substring(4,size);
    			 //descricao.replaceAll("null", "");
    			 System.out.println(descricao);
        		 post.setDescricao(descricao);
    		 }    		 
    	 }
    	 return posts;
    }
    
    public List<Post> filtragemPosts(List<Post> postList){

        List<Post> postsPichacao= new ArrayList<>();

        for (Post post : postList) {

            if(requisicaoInstaloader.getHashtag().getTag() == null) {
                if (post.getLocalizacao() != null) {
                    if (post.getLocalizacao().getLocal().equals(requisicaoInstaloader.getLocalizacao().getLocal())) {
                        if (post.getTipo().equals("pichacao")) {
                            postsPichacao.add(post);
                        }
                    }
                }
            }
            else
            {
                if (post.getTipo().equals("pichacao")) {
                    postsPichacao.add(post);
                }
            }
        }

        return postsPichacao;
    }

    public void tratamento_imgs(List<Post> postList, String path){
        BufferedImage image = null;
        Integer cont = 1;

        for (Post post : postList) {
            try {

                String aux = path+"\\"+post.getPath();
                image = ImageIO.read(new File(aux));

                image = resizeImage(image, 128,128);
                ImageIO.write(image, "jpg", new File("C:\\img_predict\\images\\out" + " " + cont + ".jpg"));
                cont++;

                System.out.println(post.getPath() + " Done");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    public List<Double> tratamentoPorcentagem(String porcentagens){  //transforma response em list

        List<Double> porcentagemList = new ArrayList<>();

        String s1= porcentagens;
        String replace = s1.replace("[","");
        String replace1 = replace.replace("]","");
        String replace2 = replace1.replace("\n",",");
        replace2 = replace2.replace(" ","");
        List<String> myList = new ArrayList<String>(Arrays.asList(replace2.split(",")));
        porcentagemList.addAll(myList.stream().map(Double::valueOf).collect(Collectors.toList()));

        return porcentagemList;
    }

    public List<Post> submetendoPorcentagemSimilaridade(List<Post> postList, String porcentagem){

        List<Double> porcentagens = tratamentoPorcentagem(porcentagem);
        Integer cont = 0;

        for (Post post : postList) {

            post.setPorcentagemSimilaridade(porcentagens.get(cont));
            cont++;
        }

        Collections.sort(postList,  Collections.reverseOrder(new Comparator<Post>(){
            public int compare(Post o1, Post o2) {
                if (o1.getPorcentagemSimilaridade() == null || o2.getPorcentagemSimilaridade() == null)
                    return 0;
                return o1.getPorcentagemSimilaridade().compareTo(o2.getPorcentagemSimilaridade());
            }
        }));


        return postList;
    }
    
    public RequisicaoInstaloader getRequisicaoInstaloader() {
		return requisicaoInstaloader;
	}

	public void setRequisicaoInstaloader(RequisicaoInstaloader requisicaoInstaloader) {
		this.requisicaoInstaloader = requisicaoInstaloader;
	}

	public EnviaRequisicaoFlask getEnviaRequisicaoFlask() {
		return enviaRequisicaoFlask;
	}

	public void setEnviaRequisicaoFlask(EnviaRequisicaoFlask enviaRequisicaoFlask) {
		this.enviaRequisicaoFlask = enviaRequisicaoFlask;
	}

	public ImgComparacao getImgComparacao() {
		return imgComparacao;
	}

	public void setImgComparacao(ImgComparacao imgComparacao) {
		this.imgComparacao = imgComparacao;
	}
	
    public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	   
    public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
