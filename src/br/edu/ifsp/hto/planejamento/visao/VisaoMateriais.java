package br.edu.ifsp.hto.planejamento.visao;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.edu.ifsp.hto.planejamento.controle.PlanejamentoControle;
import br.edu.ifsp.hto.planejamento.modelo.VO.MaterialVO;

public class VisaoMateriais {

    private PlanejamentoControle controle = new PlanejamentoControle();
    private DefaultTableModel modeloTabela;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VisaoMateriais().criarTela());
    }

    private void criarTela() {
        JFrame janela = new JFrame("Planejamento de Produção - Materiais");
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

        JLabel titulo = new JLabel("Cadastro de Materiais");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 18f));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        painelFormulario.add(titulo, gbc);

        gbc.gridwidth = 1;

        // Linha 1 - Nome do Material
        JLabel lblNome = new JLabel("Nome do Material:");
        gbc.gridy = 1;
        gbc.gridx = 0;
        painelFormulario.add(lblNome, gbc);

        JTextField txtNome = new JTextField(25);
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        painelFormulario.add(txtNome, gbc);
        gbc.gridwidth = 1;

        // Linha 2 - Quantidade + Unidade
        JLabel lblQuantidade = new JLabel("Quantidade:");
        JLabel lblUnidade = new JLabel("Unidade de Medida:");

        gbc.gridy = 3;
        gbc.gridx = 0;
        painelFormulario.add(lblQuantidade, gbc);

        gbc.gridx = 2;
        painelFormulario.add(lblUnidade, gbc);

        JTextField txtQuantidade = new JTextField(10);
        String[] unidades = { "kg", "litros", "metros", "unidades" };
        JComboBox<String> cbUnidade = new JComboBox<>(unidades);

        gbc.gridy = 4;
        gbc.gridx = 0;
        painelFormulario.add(txtQuantidade, gbc);

        gbc.gridx = 2;
        painelFormulario.add(cbUnidade, gbc);

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
                float quantidade = Float.parseFloat(txtQuantidade.getText().trim());
                String unidade = (String) cbUnidade.getSelectedItem();

                MaterialVO material = new MaterialVO(associadoId, nome, quantidade, unidade);
                // usa PlanejamentoControle -> MaterialDAO
                controle.inserir(material);

                carregarMateriaisNaTabela();

                JOptionPane.showMessageDialog(janela, "Material salvo com sucesso!");

                limparCampos(txtNome, txtQuantidade, txtAssociadoId, cbUnidade);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                    janela,
                    "Erro ao salvar material. Confira os campos (use ponto para decimais).",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // Ação: Limpar
        btnLimpar.addActionListener(e -> {
            limparCampos(txtNome, txtQuantidade, txtAssociadoId, cbUnidade);
        });

        // ---------- TABELA ----------
        String[] colunas = { "ID", "Associado", "Nome", "Quantidade", "Unidade" };
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabelaMateriais = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaMateriais);
        scrollTabela.setBorder(BorderFactory.createTitledBorder("Materiais cadastrados"));

        carregarMateriaisNaTabela();

        // Monta painel central
        painelCentral.add(painelFormulario, BorderLayout.NORTH);
        painelCentral.add(scrollTabela, BorderLayout.CENTER);

        // Adiciona na janela
        janela.add(painelMenu, BorderLayout.WEST);
        janela.add(painelCentral, BorderLayout.CENTER);

        janela.setVisible(true);
    }

    private void limparCampos(JTextField txtNome,
                              JTextField txtQuantidade,
                              JTextField txtAssociadoId,
                              JComboBox<String> cbUnidade) {
        txtNome.setText("");
        txtQuantidade.setText("");
        txtAssociadoId.setText("");
        cbUnidade.setSelectedIndex(0);
    }

    private void carregarMateriaisNaTabela() {
        modeloTabela.setRowCount(0);

        List<MaterialVO> lista = controle.listarMateriais();
        if (lista == null) return;

        for (MaterialVO m : lista) {
            Object[] linha = new Object[] {
                m.getId(),
                m.getAssociadoId(),
                m.getNome(),
                m.getQuantidade(),
                m.getUnidadeMedida()
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
