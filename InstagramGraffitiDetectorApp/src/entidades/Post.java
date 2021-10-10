package entidades;

import posts.LocalizacaoPost;

public class Post {

    private String path;
    private String tipo;
    private String descricao;
    private LocalizacaoPost localizacao;
    private Double porcentagemSimilaridade;
    private String pathAbs;


	@Override
    public String toString() {
        return "Post{" +
                "path='" + path + '\'' +
                ", tipo='" + tipo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", localizacao=" + localizacao +
                ", porcentagemSimilaridade=" + porcentagemSimilaridade +
                '}';
    }

    public String getPath() {
        return path;
    }

	public void setPath(String path) {
        this.path = path;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public LocalizacaoPost getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(LocalizacaoPost localizacao) {
        this.localizacao = localizacao;
    }

    public Double getPorcentagemSimilaridade() {
        return porcentagemSimilaridade;
    }

    public void setPorcentagemSimilaridade(Double porcentagemSimilaridade) {
        this.porcentagemSimilaridade = porcentagemSimilaridade;
    }

    public String getPathAbs() {
		return pathAbs;
	}

	public void setPathAbs(String pathAbs) {
		this.pathAbs = pathAbs;
	}
}
