module hello {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires java.desktop;
	requires org.apache.httpcomponents.httpclient;
	requires java.net.http;
	requires json;
	requires org.apache.httpcomponents.httpcore;
	requires commons.codec;
	requires org.apache.commons.io;
	requires commons.logging;
	
	opens interface_grafica to javafx.graphics, javafx.fxml;
}
