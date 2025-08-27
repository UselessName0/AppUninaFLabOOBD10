package Entities;

import DAO.IngredienteDAO;

public class Ingrediente {
	
	//ATTRIBUTI
	private String ID_Ingrediente;
	private String Nome;
	
	//COSTRUTTORI
	public Ingrediente(String IDIngrediente,String Nome) {
		this.ID_Ingrediente = IDIngrediente;
		this.Nome = Nome;
	}
	public Ingrediente(String IDIngrediente) {
		IngredienteDAO ingredienteDAO = new IngredienteDAO();
		this.ID_Ingrediente = IDIngrediente;
		this.Nome = ingredienteDAO.GetNomeIngredienteDAO(IDIngrediente);
	}
	
	public Ingrediente() {
	}
	
	//METODI
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