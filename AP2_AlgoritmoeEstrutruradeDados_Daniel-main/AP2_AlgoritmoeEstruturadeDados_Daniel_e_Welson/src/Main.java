// ==========================
// Classe No (Node)
// ==========================
class No {
    int valor;
    No esquerda, direita;

    public No(int valor) {
        this.valor = valor;
        esquerda = direita = null;
    }
}

// ==========================
// Classe ArvoreBinariaDeBusca (BST)
// ==========================
class ArvoreBinariaDeBusca {
    No raiz;

    // Inserir valores na árvore
    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    private No inserirRecursivo(No atual, int valor) {
        if (atual == null) {
            return new No(valor);
        }

        if (valor < atual.valor) {
            atual.esquerda = inserirRecursivo(atual.esquerda, valor);
        } else if (valor > atual.valor) { // evita duplicados
            atual.direita = inserirRecursivo(atual.direita, valor);
        }
        // se for igual, não faz nada (não insere duplicado)
        return atual;
    }

    // Percurso em ordem (ordenado)
    public void emOrdem() {
        System.out.print("\nPercurso em ordem (ordenado): ");
        emOrdemRecursivo(raiz);
        System.out.println();
    }

    private void emOrdemRecursivo(No atual) {
        if (atual != null) {
            emOrdemRecursivo(atual.esquerda);
            System.out.print(atual.valor + " ");
            emOrdemRecursivo(atual.direita);
        }
    }

    // Exibir árvore em pé (como um diagrama)
    public void exibirArvoreEmPe() {
        System.out.println("\n=== Estrutura da Árvore (Vertical) ===");
        exibirArvoreRecursivo(raiz, "", true);
    }

    private void exibirArvoreRecursivo(No no, String prefixo, boolean ehUltimo) {
        if (no != null) {
            System.out.print(prefixo);
            System.out.print(ehUltimo ? "└── " : "├── ");
            System.out.println(no.valor);

            String novoPrefixo = prefixo + (ehUltimo ? "    " : "│   ");
            if (no.esquerda != null || no.direita != null) {
                if (no.esquerda != null)
                    exibirArvoreRecursivo(no.esquerda, novoPrefixo, no.direita == null);
                if (no.direita != null)
                    exibirArvoreRecursivo(no.direita, novoPrefixo, true);
            }
        }
    }

    // Calcula altura da árvore
    int altura(No no) {
        if (no == null)
            return 0;
        return 1 + Math.max(altura(no.esquerda), altura(no.direita));
    }

    // Exibe folhas (nós sem filhos)
    void exibirFolhas(No no) {
        if (no != null) {
            if (no.esquerda == null && no.direita == null) {
                System.out.print(no.valor + " ");
            }
            exibirFolhas(no.esquerda);
            exibirFolhas(no.direita);
        }
    }

    // Constrói automaticamente a árvore a partir do RA
    public void construirComRA(String ra) {
        System.out.println("RA usado: " + ra);
        for (char c : ra.toCharArray()) {
            if (Character.isDigit(c)) {
                inserir(Character.getNumericValue(c));
            }
        }
    }
}

// ==========================
// Classe Principal (Main)
// ==========================
public class Main {
    public static void main(String[] args) {
        ArvoreBinariaDeBusca bst = new ArvoreBinariaDeBusca();

        // RA do Daniel
        String ra = "2403844"; // <- usa este para ficar igual ao desenho (sem repetir 4)

        // Constrói a árvore
        bst.construirComRA(ra);

        // ======= SAÍDA 1: Informações principais =======
        System.out.println("\n=== Árvore Binária de Busca (BST) ===");
        System.out.println("Raiz: " + bst.raiz.valor);

        System.out.print("Folhas: ");
        bst.exibirFolhas(bst.raiz);
        System.out.println();

        System.out.println("Altura da árvore: " + bst.altura(bst.raiz));

        bst.emOrdem();

        // ======= SAÍDA 2: Exibir árvore em pé =======
        bst.exibirArvoreEmPe();

        // ======= SAÍDA 3: Percurso em ordem novamente =======
        bst.emOrdem();
    }
}

