package Entities;

public class IngredienteRicetta {
	//ATTRIBUTI
	private Ricetta R;
	private Ingrediente I;
	private float quantità;
	private String UnitaDiMisura;
	
	//COSTRUTTORI
	public IngredienteRicetta(Ricetta R, Ingrediente I, float quantità, String UnitaDiMisura) {
		this.R = R;
		this.I = I;
		this.quantità = quantità;
		this.UnitaDiMisura = UnitaDiMisura;
	}
	
	public IngredienteRicetta() {
	}

		//METODI
		//GETTER AND SETTER
		public Ricetta getR() {
			return R;
		}
		public void setR(Ricetta r) {
			R = r;
		}
		public Ingrediente getI() {
			return I;
		}
		public void setI(Ingrediente i) {
			I = i;
		}
		public float getQuantità() {
			return quantità;
		}
		public void setQuantità(float quantità) {
			this.quantità = quantità;
		}
		public String getUnitaDiMisura() {
			return UnitaDiMisura;
		}
		public void setUnitaDiMisura(String unitaDiMisura) {
			UnitaDiMisura = unitaDiMisura;
		}
		
}
