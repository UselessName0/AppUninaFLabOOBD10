package Entities;

public class Ingrediente {
	
	//ATTRIBUTI
	private String ID_Ingrediente;
	private String Nome;
	
	//COSTRUTTORI
	public Ingrediente(String IDIngrediente,String Nome) {
		this.ID_Ingrediente = IDIngrediente;
		this.Nome = Nome;
	}
	

	//METODI
		//GETTER AND SETTER
		public String getNome() {
			return Nome;
		}
		public void setNome(String nome) {
			Nome = nome;
		}
		public String getIDIngrediente() {
			return ID_Ingrediente;
		}
		public void setIDIngrediente(String iDIngrediente) {
			ID_Ingrediente = iDIngrediente;
		}
			
}
