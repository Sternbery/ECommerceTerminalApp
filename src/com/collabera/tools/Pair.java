package com.collabera.tools;
public class Pair<A, B> {
	private A t1;
	private B t2;
	public A getT1() {
		return t1;
	}
	public void setT1(A t1) {
		this.t1 = t1;
	}
	public B getT2() {
		return t2;
	}
	public void setT2(B t2) {
		this.t2 = t2;
	}
	
	public Pair(A t1, B t2) {
		this.t1 = t1;
		this.t2 = t2;
	}

	@Override
	public boolean equals(Object other) {
		try {
			@SuppressWarnings("unchecked")
			Pair<A,B> p = (Pair<A,B>) other;
			return this.getT1().equals(p.getT1()) && this.getT2().equals(p.getT2());
		}catch(Exception e) {
			return false;
		}
	}
	@Override
	public String toString() {
		return "Pair["+this.getT1()+","+this.getT2()+"]";
	}
	@Override
	public int hashCode() {
		return this.getT1().hashCode() + this.getT2().hashCode() + 1;
	}
}
