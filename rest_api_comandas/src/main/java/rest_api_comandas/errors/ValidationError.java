package rest_api_comandas.errors;

public class ValidationError extends RuntimeException {
	private static final long serialVersionUID = 6058723476455173515L;

	 public ValidationError(String mensagem) {
	        super(mensagem);
	    }
}
