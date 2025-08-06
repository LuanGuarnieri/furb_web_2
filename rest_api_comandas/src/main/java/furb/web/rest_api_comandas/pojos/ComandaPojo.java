package furb.web.rest_api_comandas.pojos;

import java.util.List;

public class ComandaPojo {
    private Long id;
    private Long idUsuario;
    private String nomeUsuario;
    private String telefoneUsuario;
    private List<ProdutoPojo> produtos;
    
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public String getTelefoneUsuario() {
		return telefoneUsuario;
	}
	
	public void setTelefoneUsuario(String telefoneUsuario) {
		this.telefoneUsuario = telefoneUsuario;
	}
	
	public List<ProdutoPojo> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(List<ProdutoPojo> produtos) {
		this.produtos = produtos;
	}

}
