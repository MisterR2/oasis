import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

class TreePanel extends JPanel {
    private No raiz;
    private List<Rectangle> nodeBounds;
    private List<No> nodes;

    public TreePanel(No raiz) {
        this.raiz = raiz;
        this.nodeBounds = new ArrayList<>();
        this.nodes = new ArrayList<>();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (int i = 0; i < nodeBounds.size(); i++) {
                    if (nodeBounds.get(i).contains(e.getPoint())) {
                        No no = nodes.get(i);
                        Livro livro = no.getLivro();
                        JOptionPane.showMessageDialog(TreePanel.this,
                                "Título: " + livro.getTitulo() + "\n" +
                                "Autor: " + livro.getAutor() + "\n" +
                                "Gênero: " + livro.getGenero() + "\n" +
                                "Quantidade de Páginas: " + livro.getQntdPag(),
                                "Informações do Livro",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
            }
        });
    }

    public void setRaiz(No raiz) {
        this.raiz = raiz;
        repaint(); // Atualiza a renderização quando a raiz é alterada
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        nodeBounds.clear();
        nodes.clear();
        if (raiz != null) {
            desenharNo(g, raiz, getWidth() / 2, 30, getWidth() / 4);
        }
    }

    private void desenharNo(Graphics g, No no, int x, int y, int xOffset) {
        if (no == null) return;

        String titulo = no.getLivro().getTitulo();
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(titulo);
        int textHeight = fm.getHeight();
        int padding = 10;

        int rectWidth = textWidth + padding * 2;
        int rectHeight = textHeight + padding * 2;

        // Desenha o nó como um retângulo com borda azul de espessura 6px
        g.setColor(Color.BLUE);
        for (int i = 0; i < 6; i++) {
            g.drawRect(x - rectWidth / 2 - i, y - rectHeight / 2 - i, rectWidth + 2 * i, rectHeight + 2 * i);
        }

        // Desenha o título do livro dentro do nó
        g.setColor(Color.BLACK);
        g.drawString(titulo, x - textWidth / 2, y + textHeight / 4);

        // Armazena a posição e o tamanho do nó para detecção de clique
        nodeBounds.add(new Rectangle(x - rectWidth / 2, y - rectHeight / 2, rectWidth, rectHeight));
        nodes.add(no);

        if (no.getEsquerda() != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x, y + rectHeight / 2, x - xOffset, y + 50 - rectHeight / 2);
            desenharNo(g, no.getEsquerda(), x - xOffset, y + 50, xOffset / 2);
        }

        if (no.getDireita() != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x, y + rectHeight / 2, x + xOffset, y + 50 - rectHeight / 2);
            desenharNo(g, no.getDireita(), x + xOffset, y + 50, xOffset / 2);
        }
    }
}
