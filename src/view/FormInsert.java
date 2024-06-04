package view;

import dao.DaoAluno;
import modelo.Aluno;

public class FormInsert {

	public static void main(String[] args) {
		Aluno aluno = new Aluno("Ruan M. Alexandre", "mruan302@gmail.com", 22);
		DaoAluno dao = new DaoAluno();
		
		dao.insert(aluno);
		
	}

}
