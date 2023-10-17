package br.com.leisure.imovel.model;
public class Imovel {
	
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	private String nomeFornecedor;
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}
	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
	
	
	private String emailFornecedor;
	public String getEmailFornecedor() {
		return emailFornecedor;
	}
	public void setEmailFornecedor(String emailFornecedor) {
		this.emailFornecedor = emailFornecedor;
	}
	
	
	private String endereco;
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
	private String estado;
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	private String tipo;
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	private String descricao;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	private String informacao;
	public String getInformacao() {
		return informacao;
	}
	public void setInformacao(String informacao) {
		this.informacao = informacao;
	}
	

	private float area;
	public float getArea() {
		return area;
	}
	public void setArea(float area) {
		this.area = area;
	}
	
	
	private double preco;
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
	private byte vaga;
	public byte getVaga() {
		return vaga;
	}
	public void setVaga(byte vaga) {
		this.vaga = vaga;
	}
	
	public Imovel() {
		
	}
	
	public Imovel(int id, String nomeFornecedor, String emailFornecedor, String endereco, String estado, String descricao,
			String informacao, String tipo, float area, int preco, byte vaga) {
		this.id = id;
		this.nomeFornecedor = nomeFornecedor;
		this.emailFornecedor = emailFornecedor;
		this.endereco = endereco;
		this.estado = estado;
		this.tipo = tipo;
		this.descricao = descricao;
		this.informacao = informacao;
		this.area = area;
		this.preco = preco;
		this.vaga = vaga;
	}
}
