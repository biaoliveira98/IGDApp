package interface_grafica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import entidades.Post;
import instaloader.RequisicaoInstaloader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
		        "Hashtag",
		        "Perfil"
	);
	
	@FXML
    private ComboBox<String> comboData;

    @FXML
    private ComboBox<String> comboTipoEntrada;
    
    @FXML
    private DatePicker dataSelector;

    @FXML
    private Button enviarRequisicao;

    @FXML
    private Label mensagemData;

    @FXML
    private Label mensagemLocalizacao;

    @FXML
    private Label mensagemLogin;

    @FXML
    private Label mensagemTipoEntrada;

    @FXML
    private Label nomeImg;

    @FXML
    private Button okData;

    @FXML
    private Button okLocalizacao;

    @FXML
    private Button okLogin;

    @FXML
    private Button okSelecionaImg;

    @FXML
    private Button okTipoEntrada;

    @FXML
    private TitledPane panelData;

    @FXML
    private AnchorPane panelDescricao;

    @FXML
    private TitledPane panelImagemUsuario;

    @FXML
    private TilePane panelImgs;

    @FXML
    private TitledPane panelLocalizacao;

    @FXML
    private TitledPane panelLogin;

    @FXML
    private ScrollPane panelPosts;

    @FXML
    private ScrollPane panelScrollText;

    @FXML
    private TitledPane panelTipoEntrada;

    @FXML
    private Button selecionaImg;

    @FXML
    private TilePane text;

    @FXML
    private TextField textLocalizacao;

    @FXML
    private PasswordField textLoginSenha;

    @FXML
    private TextField textLoginUsuario;

    @FXML
    private TextField textTipoEntrada;

    private FileChooser fileChooser;
    private File file;    
    private InstagramGraffitiDetector instagramGraffitiDetector;

    
    @FXML
    private void okLoginAction(ActionEvent event) {
		System.out.println("aqui");
		
		String usuario, senha;
		
		usuario = textLoginUsuario.getText();
		senha = textLoginSenha.getText();
		
		System.out.println("Usuario: " + usuario);
		mensagemLogin.setText("");
		System.out.println("Senha: " + senha);
		
		instagramGraffitiDetector.getRequisicaoInstaloader().getUsuario().setNomePerfil(usuario);
		instagramGraffitiDetector.getRequisicaoInstaloader().getUsuario().setSenha(senha);
		System.out.println(instagramGraffitiDetector.getRequisicaoInstaloader().getUsuario().getNomePerfil());
		
		mensagemLogin.setTextFill(Color.web("#41cd44"));
		mensagemLogin.setText("Informações confirmadas!");
		
		textLocalizacao.setDisable(false);
		okLocalizacao.setDisable(false);
		
		
	}
    
    @FXML
    private void okLocalizacaoAction(ActionEvent event) {
		System.out.println("aqui");
		
		String localizacao;
		
		localizacao = textLocalizacao.getText();
		mensagemLocalizacao.setText("");
		
		System.out.println("Localizacao: " + localizacao);
		
		instagramGraffitiDetector.getRequisicaoInstaloader().getLocalizacao().setLocal(localizacao);

		mensagemLocalizacao.setTextFill(Color.web("#41cd44"));
		mensagemLocalizacao.setText("Informações confirmadas!");
		
		comboTipoEntrada.setDisable(false);
		textTipoEntrada.setDisable(false);
		okTipoEntrada.setDisable(false);
		
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
			if(tipoEntrada.equals("Perfil")) {
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
		
		mensagemTipoEntrada.setTextFill(Color.web("#41cd44"));
    	mensagemTipoEntrada.setText("");
		mensagemTipoEntrada.setText("Informações confirmadas!");
		
		dataSelector.setDisable(false);
		comboData.setDisable(false);
		okData.setDisable(false);
		enviarRequisicao.setDisable(false);
		
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
        
        mensagemData.setTextFill(Color.web("#41cd44"));
        mensagemData.setText("Informações confirmadas!");
				
	}
    
    @FXML
    private void enviarRequisicaoAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.WARNING);
    	List<Post> posts;
    	
    	try {
    		
        	alert.setTitle("Acessando o Instagram...");
        	alert.setHeaderText("Sistema filtrando postagens!");
        	alert.setContentText("Por favor, aguarde...");
        	alert.showAndWait();
        	
			posts = instagramGraffitiDetector.detector();
			
			Alert alert2 = new Alert(AlertType.CONFIRMATION);
	    	alert2.setTitle("Informações baixadas!");
	    	alert2.setHeaderText("Sistema concluiu filtragem das postagens!");
	    	alert2.setContentText("Pronto para continuar...");
	    	alert2.showAndWait();
	    	
	    	//displayImages(posts);
	    	selecionaImg.setDisable(false);
	    	okSelecionaImg.setDisable(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     	
    	
    }
    
    @FXML
    private void selecionaImgAction(ActionEvent event) {
    	fileChooser = new FileChooser();
    	
    	file = fileChooser.showOpenDialog(panelImagemUsuario.getContextMenu());
        if (file != null) {
           // openFile(file);
        	System.out.println(file.getAbsolutePath().toString());
        	nomeImg.setText(file.getName().toString());

        }
    }
    
    @FXML
    private void okSelecionaImgAction(ActionEvent event){
    	System.out.println("Dados enviados para o modelo de redes siamesas...");
    	List<Post> posts;
    	panelImgs.getChildren().remove(BorderPane.class);
    	
    	if(file.exists() && instagramGraffitiDetector.getStatus()) {
    		try {
    			Alert alert = new Alert(AlertType.WARNING);
    			alert.setTitle("Processando dados!");
    			alert.setHeaderText("Sistema procurando postagens com pichações similares!");
    	    	alert.setContentText("Por favor, aguarde...");
    	    	alert.showAndWait();
	    		posts = instagramGraffitiDetector.siameseModelPredict(file.getAbsolutePath().toString());
	    		Alert alert2 = new Alert(AlertType.CONFIRMATION);
		    	alert2.setTitle("Processamento finalizado!");
		    	alert2.setHeaderText("Sistema concluiu procura!");
		    	alert2.setContentText("Tudo pronto!");
		    	alert2.showAndWait();
		    	displayImages(posts);
    		} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	else
    		System.out.println("Dados faltando...");    	
    }
    
  
    private void displayImages(List<Post> posts) {
    	 TextArea descricao= new TextArea();
    	
        for (Post post : posts) {
        	File file = new File(post.getPathAbs());
        	BorderPane borderPane = new BorderPane();
	        ImageView imageView = new ImageView();
            Image image = null;
			try {
				image = new Image(new FileInputStream(file));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            imageView.setImage(image);
            imageView.setImage(image);
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            borderPane.setCenter(imageView);
	            borderPane.setOnMouseClicked((click) -> {	
	            	descricao.clear();
	                text.setStyle("-fx-background-color: WHITE");               
	                descricao.setText(post.getDescricao());
	                descricao.setEditable(false);
	                descricao.setWrapText(true);      	        
                });	           
	        text.getChildren().add(descricao);
            borderPane.setStyle("-fx-background-color: WHITE");
            borderPane.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
            panelImgs.getChildren().add(borderPane);
            
          
        }
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		comboData.setItems(optionsComboData);
		comboTipoEntrada.setItems(optionsComboTipoEntrada);
		instagramGraffitiDetector = new InstagramGraffitiDetector() ;
		

	}
	
	
	

}
