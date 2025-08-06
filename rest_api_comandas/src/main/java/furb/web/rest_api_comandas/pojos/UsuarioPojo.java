package furb.web.rest_api_comandas.pojos;

public class UsuarioPojo {
    private Long idUsuario;
    private String nomeUsuario;
    private String telefoneUsuario;
	
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

}
