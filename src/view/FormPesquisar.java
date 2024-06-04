package view;

import java.util.List;

import dao.DaoAluno;
import modelo.Aluno;

public class FormPesquisar {

	public static void main(String[] args) {
        DaoAluno dao = new DaoAluno();
        List <Aluno> pesquisaAlunos = dao.select();

        pesquisaAlunos = dao.selectByNome("Maria");

        for (Aluno aluno : pesquisaAlunos){
                System.out.println("ID: " + aluno.getId());
                System.out.println("Nome: " + aluno.getNome());
                System.out.println("E-mail: " + aluno.getEmail());
                System.out.println("Idade: " + aluno.getIdade());
                System.out.println();
        }
    }
}
