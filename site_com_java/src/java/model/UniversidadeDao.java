package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

public class UniversidadeDao {

    private EntityManager manager;

    private void conectar() {
        manager = Persistence.createEntityManagerFactory("site_com_javaPU").createEntityManager();
    }

    public int salvarCurso(Curso curso) {
        conectar();
        try {
            manager.getTransaction().begin();
            manager.persist(curso); //salva os dados na tabela do BD      
            manager.getTransaction().commit();
            return 1;
        } catch (RollbackException erro) {
            return 2;
        } catch (Exception erro) {//duplicação de chave primaria
            return 0;
        }
    }

    public int excluirCurso(String codigo) {
        conectar();
        Curso curso = manager.find(Curso.class, codigo);
        if (curso == null) { //nao encontrou o curso
            return 0;
        }else {//encontrou o curso
            manager.getTransaction().begin();
            manager.remove(curso);
            manager.getTransaction().commit();
            return 1;
        }
    }

    public List<Curso> listarCursos() {
        conectar();
        List<Curso> listaCursos = manager.createNamedQuery("Curso.findAll", Curso.class).getResultList();
        return listaCursos;
    }
    
    public Curso buscarCurso(String codigo){
        conectar();
        Curso curso = manager.find(Curso.class, codigo);
        return curso;
    }
    
    public int  alterarCurso(String codigo, String nome, String duracao){
        conectar();
        try{
        Curso curso = manager.find(Curso.class, codigo);
        curso.setNome(nome);
        curso.setDuracao(duracao);
        manager.getTransaction().begin();
        manager.merge(curso); //update
        manager.getTransaction().commit();
        return 1;
        }catch(Exception erro){
            return 0;
        }
    }
}
