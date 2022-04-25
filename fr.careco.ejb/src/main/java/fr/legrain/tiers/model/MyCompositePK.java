package fr.legrain.tiers.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class MyCompositePK implements java.io.Serializable { 

	private static final long serialVersionUID = 2516638914789443282L;

//	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//	@JoinColumn(name = "societeA")
	@Column(name="societeA")
	//private UserCompany societeA;
	private Integer A;

//	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//	@JoinColumn(name = "societeB")
	@Column(name="societeB")
	//private UserCompany societeB;
	private Integer B;

	public Integer getA() {
		return A;
	}

	public Integer getB() {
		return B;
	}

	public void setA(Integer societeA) {
		this.A = societeA;
	}

	public void setB(Integer societeB) {
		this.B = societeB;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((A == null) ? 0 : A.hashCode());
		result = prime * result
				+ ((B == null) ? 0 : B.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyCompositePK other = (MyCompositePK) obj;
		if (A == null) {
			if (other.A != null)
				return false;
		} else if (!A.equals(other.A))
			return false;
		if (B == null) {
			if (other.B != null)
				return false;
		} else if (!B.equals(other.B))
			return false;
		return true;
	}

}
