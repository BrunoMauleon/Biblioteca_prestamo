package mx.azurian.biblioteca.entity;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "libros")
public class Libro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_libro;
	
	@NotEmpty
	private String titulo;
	@NotEmpty
	private String autor;
	@NotEmpty
	private String tipo; 
	@NotEmpty
	private String sinopsis;
	@NotNull
	private int noPaginas;
	@Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean disponible;
	@OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Prestamo> prestamos;
	public Long getId_libro() {
		return id_libro;
	}

	public void setId_libro(Long id_libro) {
		this.id_libro = id_libro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public int getNoPaginas() {
		return noPaginas;
	}

	public void setNoPaginas(int noPaginas) {
		this.noPaginas = noPaginas;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	
	
}
