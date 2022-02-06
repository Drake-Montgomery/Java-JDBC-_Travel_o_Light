package model;

import java.sql.Date;

public class Destino {

	private String Origem;
	private String Destino;
	private Date Data_Ida;
	private Date Data_Volta;
	
	
	public Destino(String origem, String destino, Date data_Ida, Date data_Volta) {
		Origem = origem;
		Destino = destino;
		Data_Ida = data_Ida;
		Data_Volta = data_Volta;
	}
	
	public Destino(String origem, String destino, Date data_Ida) {
		Origem = origem;
		Destino = destino;
		Data_Ida = data_Ida;
	}

	public String getOrigem() {
		return Origem;
	}
	
	public void setOrigem(String origem) {
		Origem = origem;
	}
	
	public String getDestino() {
		return Destino;
	}
	
	public void setDestino(String destino) {
		Destino = destino;
	}
	
	public Date getData_Ida() {
		return Data_Ida;
	}
	
	public void setData_Ida(Date data_Ida) {
		Data_Ida = data_Ida;
	}
	
	public Date getData_Volta() {
		return Data_Volta;
	}
	
	public void setData_Volta(Date data_Volta) {
		Data_Volta = data_Volta;
	}
	
	
}
