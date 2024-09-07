package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Curso;
import model.UniversidadeDao;

@WebServlet(name = "ControleCursos", urlPatterns = {"/ControleCursos"})
public class ControleCursos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Aqui você recebe os dados que vem da camada View (formulários)
        String flag, mensagem = null;
        //Recebe o conteúdo da variável flag que veio do forulário HTML
        flag = request.getParameter("flag");
        if (flag.equals("cadastro")) {
            // Receber os dados digitados no formulário
            String c, n, d;
            c = request.getParameter("codigo");
            n = request.getParameter("nome");
            d = request.getParameter("duracao");
            //Realizar o cadastro na tabela do BD
            //Objeto da classe Curso
            Curso curso = new Curso();
            curso.setCodigo(c);
            curso.setNome(n);
            curso.setDuracao(d);
            //Enviar o objeto curso para o  metodo
            //salvarCurso da classe UniversaidadeDao
            int resultado = new UniversidadeDao().salvarCurso(curso);
            if (resultado == 1) {
                mensagem = "Cadastro realizado com sucesso";
            } else if (resultado == 2) {
                mensagem = "Código do curso já cadastrado";
            } else {
                mensagem = "Erro ao tentar salvar o curso";
            }
            //carrega a mensagem em uma variavel "mensagem"
            request.setAttribute("mensagem", mensagem);
            //indica para qual arquivo jsp a mensagem sera enviada
            RequestDispatcher disp = request.getRequestDispatcher("mensagens.jsp");
            //envia a mensagem para o arquivo mensagens.jsp
            disp.forward(request, response);
        } else if (flag.equals("excluir")) {
            //aqui sera feita a parte de exclusao de cursos
            String c;
            c = request.getParameter("codigo");
            //Chamar o método excluirCurso na classe UniversidadeDao
            int resultado = new UniversidadeDao().excluirCurso(c);
            if (resultado == 1) { //Se ele excluiu o curso
                mensagem = "Curso excluído com sucesso";
            } else { //Se o curso não foi encontrado
                mensagem = "Curso não está cadastrado";
            }
            //carrega a mensagem em uma variavel "mensagem"
            request.setAttribute("mensagem", mensagem);
            //indica para qual arquivo jsp a mensagem sera enviada
            RequestDispatcher disp = request.getRequestDispatcher("mensagens.jsp");
            //envia a mensagem para o arquivo mensagens.jsp
            disp.forward(request, response);
        } else if (flag.equals("listar")) {
            List<Curso> listaCursos = new UniversidadeDao().listarCursos();//chama o metodo listarCursos da universidadeDao que recebe os cursos
            //enviar a lista de cursos para exibir em um arquivo jsp

            request.setAttribute("listaCursos", listaCursos);
            RequestDispatcher disp = request.getRequestDispatcher("listar_cursos.jsp");
            disp.forward(request, response);
        } else if (flag.equals("buscarCurso")) {
            //Buscar o curso para alteração
            String codigo = request.getParameter("codigo");
            Curso curso = new UniversidadeDao().buscarCurso(codigo);
            if (curso == null) {//se não encontrar curso
                request.setAttribute("mensagem", "Curso encontrado");
                RequestDispatcher disp = request.getRequestDispatcher("mensagens.jsp");
                disp.forward(request, response);
            } else {// se encontrar
                request.setAttribute("curso", curso);
                RequestDispatcher disp = request.getRequestDispatcher("carregacurso.jsp");
                disp.forward(request, response);

            }
        } else if (flag.equals("alteracao")) {
            String codigo ,  nome, duracao;
            codigo = request.getParameter("codigo");
            nome = request.getParameter("nome");
            duracao = request.getParameter("duracao");
            int resultado = new UniversidadeDao().alterarCurso(codigo, nome, duracao);
            if (resultado == 1) {
                mensagem = "Alteração realizada com sucesso no curso " + codigo;
            } else {
                mensagem = "Erro ao tentar alterar os dados do curso " + codigo;
            }
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher disp = request.getRequestDispatcher("mensagens.jsp");
            disp.forward(request, response);
            
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
