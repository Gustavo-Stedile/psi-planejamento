package br.edu.ifsp.hto.planejamento.visao;

import br.edu.ifsp.hto.planejamento.modelo.DAO.TalhaoDAO;

public class App {
    public static void main(String[] args) {
        
        
        TalhaoDAO talhao = new TalhaoDAO();

        talhao.buscarTalhoesPorArea(0);

    }
}