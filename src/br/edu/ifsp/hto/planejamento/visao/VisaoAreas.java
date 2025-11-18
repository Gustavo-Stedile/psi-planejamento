package br.edu.ifsp.hto.planejamento.visao;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.edu.ifsp.hto.planejamento.controle.PlanejamentoControle;
import br.edu.ifsp.hto.planejamento.modelo.VO.AreaVO;

public class VisaoAreas {

    // controle central do sistema
    private PlanejamentoControle controle = new PlanejamentoControle();
    private DefaultTableModel modeloTabela;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VisaoAreas().criarTela());
    }

    private void criarTela() {
        JFrame janela = new JFrame("Planejamento de Produção - Áreas");
        janela.setSize(900, 600);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new BorderLayout());
        janela.setLocationRelativeTo(null);

        // ===== MENU LATERAL =====
        JPanel painelMenu = new JPanel();
        painelMenu.setLayout(new BoxLayout(painelMenu, BoxLayout.Y_AXIS));
        painelMenu.setBorder(new EmptyBorder(30, 10, 15, 10));
        painelMenu.setBackground(new Color(55, 61, 13));

        String[] opcoes = { "Áreas", "Talhões", "Planos", "Atividades", "Materiais" };
        for (String opcao : opcoes) {
            painelMenu.add(criarBotao(opcao));
            painelMenu.add(Box.createVerticalStrut(10));
        }

        // ===== PAINEL CENTRAL =====
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBorder(new EmptyBorder(20, 20, 20, 20));

        // ---------- FORMULÁRIO ----------
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBorder(new EmptyBorder(10, 10, 20, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;

        JLabel titulo = new JLabel("Cadastro de Áreas");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 18f));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        painelFormulario.add(titulo, gbc);

        gbc.gridwidth = 1;

        // Linha 1 - Nome + pH
        JLabel lblNome = new JLabel("Nome da Área:");
        JLabel lblPh = new JLabel("pH do Solo:");

        gbc.gridy = 1;
        gbc.gridx = 0;
        painelFormulario.add(lblNome, gbc);

        gbc.gridx = 2;
        painelFormulario.add(lblPh, gbc);

        JTextField txtNome = new JTextField(18);
        JTextField txtPh = new JTextField(8);

        gbc.gridy = 2;
        gbc.gridx = 0;
        painelFormulario.add(txtNome, gbc);

        gbc.gridx = 2;
        painelFormulario.add(txtPh, gbc);

        // Linha 2 - Área total / utilizada
        JLabel lblAreaTotal = new JLabel("Área Total (ha):");
        JLabel lblAreaUtilizada = new JLabel("Área Utilizada (ha):");

        gbc.gridy = 3;
        gbc.gridx = 0;
        painelFormulario.add(lblAreaTotal, gbc);

        gbc.gridx = 2;
        painelFormulario.add(lblAreaUtilizada, gbc);

        JTextField txtAreaTotal = new JTextField(10);
        JTextField txtAreaUtilizada = new JTextField(10);

        gbc.gridy = 4;
        gbc.gridx = 0;
        painelFormulario.add(txtAreaTotal, gbc);

        gbc.gridx = 2;
        painelFormulario.add(txtAreaUtilizada, gbc);

        // Linha 3 - ID do associado
        JLabel lblAssociado = new JLabel("ID do Associado:");
        JTextField txtAssociadoId = new JTextField(10);

        gbc.gridy = 5;
        gbc.gridx = 0;
        painelFormulario.add(lblAssociado, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        painelFormulario.add(txtAssociadoId, gbc);

        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JButton btnSalvar = new JButton("Salvar");
        JButton btnLimpar = new JButton("Limpar");

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnLimpar);

        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        painelFormulario.add(painelBotoes, gbc);

        // Ação: Salvar
btnSalvar.addActionListener(e -> {
    try {
        int associadoId = Integer.parseInt(txtAssociadoId.getText().trim());
        String nome = txtNome.getText().trim();
        float areaTotal = Float.parseFloat(txtAreaTotal.getText().trim());
        float areaUtilizada = Float.parseFloat(txtAreaUtilizada.getText().trim());
        float ph = Float.parseFloat(txtPh.getText().trim());

        AreaVO area = new AreaVO(associadoId, nome, areaTotal, areaUtilizada, ph);
        controle.inserir(area);
        carregarAreasNaTabela();

        JOptionPane.showMessageDialog(janela, "Área salva com sucesso!");

        // 🔹 limpa os campos depois de salvar:
        txtAssociadoId.setText("");
        txtNome.setText("");
        txtAreaTotal.setText("");
        txtAreaUtilizada.setText("");
        txtPh.setText("");

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(
            janela,
            "Erro ao salvar área. Confira os campos (use ponto para decimais).",
            "Erro",
            JOptionPane.ERROR_MESSAGE
        );
    }
});

        // Ação: Limpar
        btnLimpar.addActionListener(e -> {
            txtAssociadoId.setText("");
            txtNome.setText("");
            txtAreaTotal.setText("");
            txtAreaUtilizada.setText("");
            txtPh.setText("");
        });

        // ---------- TABELA ----------
        String[] colunas = { "ID", "Associado", "Nome", "Área Total", "Área Utilizada", "pH" };
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // só leitura por enquanto
            }
        };

        JTable tabelaAreas = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaAreas);
        scrollTabela.setBorder(BorderFactory.createTitledBorder("Áreas cadastradas"));

        carregarAreasNaTabela();

        // Monta painel central
        painelCentral.add(painelFormulario, BorderLayout.NORTH);
        painelCentral.add(scrollTabela, BorderLayout.CENTER);

        // Adiciona na janela
        janela.add(painelMenu, BorderLayout.WEST);
        janela.add(painelCentral, BorderLayout.CENTER);

        janela.setVisible(true);
    }

    private void carregarAreasNaTabela() {
        modeloTabela.setRowCount(0); // limpa

        List<AreaVO> lista = controle.listarAreas();
        if (lista == null) return;

        for (AreaVO a : lista) {
            Object[] linha = new Object[] {
                a.getId(),
                a.getAssociadoId(),
                a.getNome(),
                a.getAreaTotal(),
                a.getAreaUtilizada(),
                a.getPh()
            };
            modeloTabela.addRow(linha);
        }
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        Dimension tamanho = new Dimension(130, 30);
        botao.setPreferredSize(tamanho);
        botao.setMaximumSize(tamanho);
        botao.setMinimumSize(tamanho);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        return botao;
    }
}
