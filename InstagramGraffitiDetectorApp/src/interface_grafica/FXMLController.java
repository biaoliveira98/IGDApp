package interface_grafica;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import instaloader.RequisicaoInstaloader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import sistema.InstagramGraffitiDetector;


public class FXMLController implements Initializable{
	
	ObservableList<String> optionsComboData = 
		    FXCollections.observableArrayList(
		        "Antes de",
		        "A partir de",
		        "Igual a"
	);
	
	ObservableList<String> optionsComboTipoEntrada = 
		    FXCollections.observableArrayList(
		        "Hashtag #",
		        "Perfil @"
	);
	
	@FXML
    private ComboBox<String> comboData;

    @FXML
    private ComboBox<String> comboTipoEntrada;
    
    @FXML
    private ProgressIndicator processandoDadosProgressIndicator;

    @FXML
    private DatePicker dataSelector;

    @FXML
    private Button enviarRequisicao;

    @FXML
    private Button okData;

    @FXML
    private Button okImagemUsuario;

    @FXML
    private Button okLocalizacao;

    @FXML
    private Button okLogin;

    @FXML
    private Button okTipoEntrada;

    @FXML
    private TitledPane panelData;

    @FXML
    private TitledPane panelImagemUsuario;

    @FXML
    private TitledPane panelLocalizacao;

    @FXML
    private TitledPane panelLogin;

    @FXML
    private AnchorPane panelPosts;

    @FXML
    private TitledPane panelTipoEntrada;

    @FXML
    private Button selecionaImg;

    @FXML
    private TextField textLocalizacao;

    @FXML
    private PasswordField textLoginSenha;

    @FXML
    private TextField textLoginUsuario;

    @FXML
    private TextField textTipoEntrada;
    
    @FXML
    private Label nomeImg;
    
    @FXML
    private ImageView imageViewPosts;
    
    private InstagramGraffitiDetector instagramGraffitiDetector;

    
    @FXML
    private void okLoginAction(ActionEvent event) {
		System.out.println("aqui");
		
		String usuario, senha;
		
		usuario = textLoginUsuario.getText();
		senha = textLoginSenha.getText();
		
		System.out.println("Usuario: " + usuario);
		System.out.println("Senha: " + senha);
		
		textLoginUsuario.clear();
		textLoginSenha.clear();
		
		instagramGraffitiDetector.getRequisicaoInstaloader().getUsuario().setNomePerfil(usuario);
		instagramGraffitiDetector.getRequisicaoInstaloader().getUsuario().setSenha(senha);
		System.out.println(instagramGraffitiDetector.getRequisicaoInstaloader().getUsuario().getNomePerfil());
		
	}
    
    @FXML
    private void okLocalizacaoAction(ActionEvent event) {
		System.out.println("aqui");
		
		String localizacao;
		
		localizacao = textLocalizacao.getText();
		
		System.out.println("Localizacao: " + localizacao);
		
		textLocalizacao.clear();
		
		instagramGraffitiDetector.getRequisicaoInstaloader().getLocalizacao().setLocal(localizacao);
		
	}
    
    @FXML
    private void okDataAction(ActionEvent event) {
		System.out.println("aqui");
		
		String tipoData = comboData.getValue(), data;
		Integer numTipoData = 0;
		Integer dia, mes, ano;
		
		if (tipoData != null && !tipoData.toString().isEmpty()){
			if(tipoData.equals("A partir de"))
				numTipoData = 1;
			else if(tipoData.equals("Antes de"))
				numTipoData = 2;
			else
				numTipoData = 3;
		}   
		System.out.println("Tipo data: " + tipoData + ", " + numTipoData.toString());
		
		data = dataSelector.getValue().toString();
		System.out.println("Data: " + data);
		
		String replace = data.replace("-",",");
        replace = replace.replace(" ","");
        List<String> dataList = new ArrayList<String>(Arrays.asList(replace.split(",")));
        System.out.println(dataList.toString());
        ano = Integer.parseInt(dataList.get(0));
        mes = Integer.parseInt(dataList.get(1));
        dia = Integer.parseInt(dataList.get(2));
        System.out.println(dia + "/" + mes + "/" + ano);	
        
        instagramGraffitiDetector.getRequisicaoInstaloader().getData().setDia(dia);
        instagramGraffitiDetector.getRequisicaoInstaloader().getData().setMes(mes);
        instagramGraffitiDetector.getRequisicaoInstaloader().getData().setAno(ano);
        instagramGraffitiDetector.getRequisicaoInstaloader().getData().setOperador(numTipoData);
				
	}
    
    
    private void progressIndicator(ActionEvent event) {
    	
    	/*final Float[] values = new Float[] {-1.0f, 0f, 0.6f, 1.0f};
    	final Label [] labels = new Label[values.length];
    	final ProgressBar[] pbs = new ProgressBar[values.length];
    	final ProgressIndicator[] pins = new ProgressIndicator[values.length];
    	
    	for (int i = 0; i < values.length; i++) {
            final Label label = labels[i] = new Label();
            label.setText("progress:" + values[i]);
 
            final ProgressBar pb = pbs[i] = new ProgressBar();
            pb.setProgress(values[i]);
 
            final ProgressIndicator pin = pins[i] = new ProgressIndicator();
            pin.setProgress(values[i]);
            final HBox hb = hbs[i] = new HBox();
            hb.setSpacing(5);
            hb.setAlignment(Pos.CENTER);
            hb.getChildren().addAll(label, pb, pin);
        }*/
    }
    
    @FXML
    private void okTipoEntradaAction(ActionEvent event) {
    	
    	String tipoEntrada = comboTipoEntrada.getValue();
    	Integer numTipoEntrada = 0;
    	String nomeTipoEntrada = null;
    	
    	nomeTipoEntrada = textTipoEntrada.getText();
    	
    	if (tipoEntrada != null && !tipoEntrada.toString().isEmpty()){
			if(tipoEntrada.equals("Perfil @")) {
				instagramGraffitiDetector.getRequisicaoInstaloader().getPerfil().setNome(nomeTipoEntrada);
				numTipoEntrada = 1;
			}				
			else { 
				instagramGraffitiDetector.getRequisicaoInstaloader().getHashtag().setTag(nomeTipoEntrada);
				numTipoEntrada = 2;			
			}
		}      	
    	System.out.println("Tipo entrada: " + tipoEntrada + ", " + numTipoEntrada.toString());
    	    			
    	
		
		System.out.println("Nome: " + nomeTipoEntrada); 
    }
    
    @FXML
    private void enviarRequisicaoAction(ActionEvent event) {
    	try {
			instagramGraffitiDetector.detector();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
    @FXML
    private String selecionaImgAction(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	
    	File file = fileChooser.showOpenDialog(panelImagemUsuario.getContextMenu());
        if (file != null) {
           // openFile(file);
        	System.out.println(file.getAbsolutePath().toString());
        	nomeImg.setText(file.getName().toString());

        }
        
        return file.getAbsolutePath().toString();
    }
    
    private void okSelecionaImgAction(ActionEvent event) {
    	 /*
    	
    	pegar nome arquivo
    	*/
    }
    
    /*@FXML
    private void painelPosts(ActionEvent event) {
    	
    	StackPane sp = new StackPane();
    	Image image = new Image("C:\\Users\\biaan\\Desktop\\dataset\\com_pichacao (2).jpeg");
    	imageViewPosts = new ImageView(image);
        sp.getChildren().add(imageViewPosts);
    
		
    }*/
   
    /*
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                FileChooserSample.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }
    */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		comboData.setItems(optionsComboData);
		comboTipoEntrada.setItems(optionsComboTipoEntrada);
		
		/*
		StackPane sp = new StackPane();
    	Image image = new Image("C:\\Users\\biaan\\Desktop\\dataset\\com_pichacao (2).jpeg");
    	imageViewPosts = new ImageView(image);
        sp.getChildren().add(imageViewPosts);*/
		
		 instagramGraffitiDetector = new InstagramGraffitiDetector() ;
		 
	}
	
	
	

}
