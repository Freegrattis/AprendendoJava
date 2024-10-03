import java.text.DecimalFormat;
import java.util.ArrayList;

public class Estoque {
    ArrayList<Ferramenta> listaDeFerramentas = new ArrayList<Ferramenta>();
    ArrayList<Ferramentas> ferramentasEmEstoque = new ArrayList<Ferramentas>();

    public void adicionarFerramenta(Ferramenta f) {
        this.listaDeFerramentas.add(f);
    }

    public void adicionarFerramentaEstoque(Ferramentas fs) {
        this.ferramentasEmEstoque.add(fs);
    }

    public String listarFerramentas() {
        String titulo = "FERRAMENTAS:\n\n";
        String ferramentas = "Os dados da ferramenta são";
        DecimalFormat df = new DecimalFormat("#,###.00");
        for (Ferramentas fs : this.ferramentasEmEstoque) {
            ferramentas += "\n\nCódigo: " + fs.getFerramenta().getCodigo() + "\nMarca: " + fs.getFerramenta().getMarca()
                    + "\nNome: " + fs.getFerramenta().getNome() + "\nDescrição: " + fs.getFerramenta().getDescricao()
                    + "\nPreço: R$" + df.format(fs.getFerramenta().getPreco()) + "\nQuantidade disponível: "
                    + fs.getQuantidadeAtual();
        }
        if (ferramentas.equals("Os dados da ferramenta são")) {
            ferramentas = "Não foi possível encontrar ferramentas com essa marca!";
        }
        return titulo + ferramentas;
    }

    public Ferramenta removerPeca(int codigo) {
        return listaDeFerramentas.remove(codigo);
    }

    public String editarFerramentas() {
        String mensagem = "";
        boolean erro;
        boolean naoEhNulo;
        int codigoDigitado = 0;
        if (!this.ferramentasEmEstoque.isEmpty()) {
            do {
                try {
                    codigoDigitado = EntradaSaida.receberCaracteristicaInteiro("o codigo");
                    erro = false;
                } catch (Exception ex) {
                    EntradaSaida.mostrarAlerta("Insira somente números!");
                    erro = true;
                }
            } while (erro);
         
            for (Ferramentas fs : this.ferramentasEmEstoque) {
                if (codigoDigitado == fs.getFerramenta().getCodigo()) {
                    System.out.println("Código encontrado!");
                    do{
                        fs.getFerramenta().setNome(EntradaSaida.receberCaracteristica("o nome"));
                            if(!fs.getFerramenta().getNome().equals("")){
                                naoEhNulo=true;
                            }else{
                                EntradaSaida.mostrarAlerta("Insira um nome!");
                                naoEhNulo=false;
                        }
                }while(!naoEhNulo);
                do{
                        fs.getFerramenta().setMarca(EntradaSaida.receberCaracteristica("a marca"));
                            if(!fs.getFerramenta().getMarca().equals("")){
                                naoEhNulo=true;
                            }else{
                                EntradaSaida.mostrarAlerta("Insira uma marca!");
                                naoEhNulo=false;
                        }
                }while(!naoEhNulo);
                do{
                        fs.getFerramenta().setDescricao(EntradaSaida.receberCaracteristica("a descrição"));
                            if(!fs.getFerramenta().getDescricao().equals("")){
                                naoEhNulo=true;
                            }else{
                                EntradaSaida.mostrarAlerta("Insira uma descrição!");
                                naoEhNulo=false;
                        }
                }while(!naoEhNulo);
                do{
                    try{
                        fs.getFerramenta().setPreco(EntradaSaida.receberCaracteristicaDouble("o preço"));
                        erro=false;
                    } catch (Exception ex){
                        EntradaSaida.mostrarAlerta("Insira somente números!");
                        erro=true;
                    }
                }while(erro);
                    // QUANTIDADE MINIMA
                    do {
                        try {
                            fs.setQuantidadeMinima(
                                    EntradaSaida.receberCaracteristicaInteiro("a quantidade mínima possível"));
                            erro = false;
                        } catch (Exception ex) {
                            EntradaSaida.mostrarAlerta("Insira somente números!");
                            erro = true;
                        }
                    } while (erro);
                    return "Alterado com sucesso!";
                } else {
                    mensagem = "Codigo inválido!";
                }
            }
        } else {
            mensagem="A lista de ferramentas está vazia!";
        }
        return mensagem;
    }

    public Ferramenta excluirFerramentas(int posicao) {
        if (posicao <= -1) {
            return null;
        } else {
            this.ferramentasEmEstoque.remove(posicao);
            return this.listaDeFerramentas.remove(posicao);
        }
    }

    public int verificaExclusaoFerramenta() {
        int codigoFerramenta = 0;
        boolean erro;
        String mensagem = "";
        do {
            try {
                codigoFerramenta = EntradaSaida.receberCaracteristicaInteiro("o codigo");
                erro = false;
            } catch (Exception ex) {
                EntradaSaida.mostrarAlerta("Insira somente números!");
                erro = true;
            }
        } while (erro);

        int posicao = 0;
        for (Ferramentas fs : this.ferramentasEmEstoque) {
            for (Ferramenta f : this.listaDeFerramentas) {
                if (fs.getFerramenta().getCodigo() == codigoFerramenta
                        && fs.getFerramenta().getCodigo() == f.getCodigo()) {
                    if (fs.getQuantidadeAtual() <= 0) {
                        posicao = this.listaDeFerramentas.indexOf(f);
                        return posicao;
                    } else {
                        EntradaSaida.mostrarAlerta("Esta ferramenta ainda apresenta quantidade em estoque!");
                        posicao = -1;
                    }
                } else {
                    mensagem="O código inserido está incorreto!";
                    posicao = -1;
                }
            }
        }
        EntradaSaida.mostrarAlerta(mensagem);
        System.out.println(posicao);
        return posicao;
    }

    public String adicionarQuantidade() {
        String msg = "";
        int codigoDigitado = 0;
        boolean erro;
        int quantidadeAdicionada = 0;
        if (!this.ferramentasEmEstoque.isEmpty()) {

            do {
                try {
                    codigoDigitado = EntradaSaida.receberCaracteristicaInteiro("o codigo");
                    erro = false;
                } catch (Exception ex) {
                    EntradaSaida.mostrarAlerta("Insira somente números!");
                    erro = true;
                }
            } while (erro);
            for (Ferramentas fs : this.ferramentasEmEstoque) {
                if (codigoDigitado == fs.getFerramenta().getCodigo()) {
                    do {
                        try {
                            quantidadeAdicionada = EntradaSaida.receberCaracteristicaInteiro("a quantidade");
                            erro = false;
                        } catch (Exception ex) {
                            EntradaSaida.mostrarAlerta("Insira somente números!");
                            erro = true;
                        }
                    } while (erro);

                    int soma = 0;
                    soma = (fs.getQuantidadeAtual() + quantidadeAdicionada);
                    fs.setQuantidadeAtual(soma);
                    msg = "Adicionado!";
                    return msg;
                } else {
                    msg = "Codigo inválido!";
                }
            }
        } else {
            msg = "A lista de ferramentas está vazia!";
            return msg;
        }
        return msg;
    }

    public String removerQuantidade() {
        int codigoDigitado = 0;
        boolean erro;
        int quantidadeRemovida = 0;
        String mensagem = "";
        if (!this.ferramentasEmEstoque.isEmpty()) {
            do {
                try {
                    codigoDigitado = EntradaSaida.receberCaracteristicaInteiro("o codigo");
                    erro = false;
                } catch (Exception ex) {
                    EntradaSaida.mostrarAlerta("Insira somente números!");
                    erro = true;
                }
            } while (erro);
            for (Ferramentas fs : this.ferramentasEmEstoque) {
                if (codigoDigitado == fs.getFerramenta().getCodigo() && fs.getQuantidadeAtual() != 0) {
                    do {
                        try {
                            quantidadeRemovida = EntradaSaida.receberCaracteristicaInteiro("a quantidade");
                            erro = false;
                        } catch (Exception ex) {
                            EntradaSaida.mostrarAlerta("Insira somente números!");
                            erro = true;
                        }
                    } while (erro);
                    if (quantidadeRemovida > fs.getQuantidadeAtual()) {
                        mensagem="Não pode retirar mais do que a quantidade disponível!";
                    } else {
                        fs.setQuantidadeAtual(fs.getQuantidadeAtual() - quantidadeRemovida);
                        mensagem = "Removido!";
                        return mensagem;
                    }
                } else {
                    mensagem="Codigo inválido!";
                }
            }
           
        } else {
            EntradaSaida.mostrarAlerta("A lista de ferramentas está vazia!");
        }
        return mensagem;
    }

    public String listarFerramentasMarca() {
        String titulo = "FERRAMENTAS:\n\n";
        String ferramentas = "Os dados da ferramenta são";
        DecimalFormat df = new DecimalFormat("#,###.00");
        String marca = EntradaSaida.receberCaracteristica("a marca");
        for (Ferramentas fs : this.ferramentasEmEstoque) {
            if (fs.getFerramenta().getMarca().equals(marca)) {
                ferramentas += "\n\nCódigo: " + fs.getFerramenta().getCodigo() + "\nMarca: "
                        + fs.getFerramenta().getMarca()
                        + "\nNome: " + fs.getFerramenta().getNome() + "\nDescrição: "
                        + fs.getFerramenta().getDescricao()
                        + "\nPreço: R$" + df.format(fs.getFerramenta().getPreco()) + "\nQuantidade disponível: "
                        + fs.getQuantidadeAtual();
            }
        }
        if (ferramentas.equals("Os dados da ferramenta são")) {
            ferramentas = "Não foi possível encontrar ferramentas com essa marca!";
        }
        return titulo + ferramentas;
    }

    public String listarFerramentasCodigo() {
        String titulo = "FERRAMENTAS:\n\n";
        String ferramentas = "Os dados da ferramenta são";
        int codigo= 0;
        boolean erro;
        DecimalFormat df = new DecimalFormat("#,###.00");
        do{
            try{
                codigo = EntradaSaida.receberCaracteristicaInteiro("o código");
                erro=false;
            } catch (Exception ex){
                EntradaSaida.mostrarAlerta("Insira somente números!");
                erro=true;
            }
        }while(erro);
        
        for (Ferramentas fs : this.ferramentasEmEstoque) {
            if (fs.getFerramenta().getCodigo() == codigo) {
                ferramentas += "\n\nCódigo: " + fs.getFerramenta().getCodigo() + "\nMarca: "
                        + fs.getFerramenta().getMarca()
                        + "\nNome: " + fs.getFerramenta().getNome() + "\nDescrição: "
                        + fs.getFerramenta().getDescricao()
                        + "\nPreço: R$" + df.format(fs.getFerramenta().getPreco()) + "\nQuantidade disponível: "
                        + fs.getQuantidadeAtual();
            }
        }
        if (ferramentas.equals("Os dados da ferramenta são")) {
            ferramentas = "Não foi possível encontrar ferramentas com esse código!";
        }
        return titulo + ferramentas;
    }

    public String listarFerramentasComprar() {
        String titulo = "FERRAMENTAS:\n\n";
        String ferramentas = "Os dados da ferramenta são:";
        DecimalFormat df = new DecimalFormat("#,###.00");
        for (Ferramentas fs : this.ferramentasEmEstoque) {
            if (fs.getQuantidadeAtual() <= fs.getQuantidadeMinima()) {
                ferramentas += "\n\nCódigo: " + fs.getFerramenta().getCodigo() + "\nMarca: "
                        + fs.getFerramenta().getMarca()
                        + "\nNome: " + fs.getFerramenta().getNome() + "\nDescrição: "
                        + fs.getFerramenta().getDescricao()
                        + "\nPreço: R$" + df.format(fs.getFerramenta().getPreco()) + "\nQuantidade disponível: "
                        + fs.getQuantidadeAtual();
            }
        }
        if (ferramentas.equals("Os dados da ferramenta são:")) {
            ferramentas = "Não foi possível encontrar ferramentas a comprar!";
        }
        return titulo + ferramentas;
    }

    public Object listarFerramentasEmEstoque() {
        String titulo = "FERRAMENTAS:\n\n";
        String ferramentas = "Os dados da ferramenta são:";
        DecimalFormat df = new DecimalFormat("#,###.00");
        for (Ferramentas fs : this.ferramentasEmEstoque) {
            if (fs.getQuantidadeAtual() >= fs.getQuantidadeMinima()) {
                ferramentas += "\n\nCódigo: " + fs.getFerramenta().getCodigo() + "\nMarca: "
                        + fs.getFerramenta().getMarca()
                        + "\nNome: " + fs.getFerramenta().getNome() + "\nDescrição: "
                        + fs.getFerramenta().getDescricao()
                        + "\nPreço: R$" + df.format(fs.getFerramenta().getPreco()) + "\nQuantidade disponível: "
                        + fs.getQuantidadeAtual();
            }
        }
        if (ferramentas.equals("Os dados da ferramenta são:")) {
            ferramentas = "Não tem ferramentas em estoque!";
        }
        return titulo + ferramentas;
    }

    public int removerQuantidadeVendida(int codigo) {
        int quantidadeRemovida = 0;
        boolean erro;
        String mensagem = "";
        if (!this.ferramentasEmEstoque.isEmpty()) {
            for (Ferramentas fs : this.ferramentasEmEstoque) {
                if (codigo == fs.getFerramenta().getCodigo() && fs.getQuantidadeAtual() != 0) {
                    do {
                        try {
                            quantidadeRemovida = EntradaSaida.receberCaracteristicaInteiro("a quantidade");
                            erro = false;
                        } catch (Exception ex) {
                            EntradaSaida.mostrarAlerta("Insira somente números!");
                            erro = true;
                        }
                    } while (erro);
                    if (quantidadeRemovida > fs.getQuantidadeAtual() || quantidadeRemovida < 0) {
                        mensagem="Não pode comprar essa quantidade!";
                    } 
                    else {
                        fs.setQuantidadeAtual(fs.getQuantidadeAtual() - quantidadeRemovida);
                        return quantidadeRemovida;
                    }
                } else {
                    mensagem="Codigo inválido!";
                }
            }
        } else {
            mensagem="A lista de ferramentas está vazia!";
        }
        EntradaSaida.mostrarAlerta(mensagem);
        return quantidadeRemovida;
    }

    public double atribuirPreco(int codigoCompra) {
        for(Ferramentas fs:this.ferramentasEmEstoque){
            if(codigoCompra==fs.getFerramenta().getCodigo()){
                return fs.getFerramenta().getPreco();
            }
        }
        return -1;
    }

	public String atribuirNome(int codigoCompra) {
        for(Ferramentas fs:this.ferramentasEmEstoque){
            if(codigoCompra==fs.getFerramenta().getCodigo()){
                return fs.getFerramenta().getNome();
            }
        }
        return "";
    }

    public int setarQuantidadeProdutoComprado(int codigoCompra) {
            for(Ferramentas fs:this.ferramentasEmEstoque){
                if (codigoCompra==fs.getFerramenta().getCodigo()){
                       return removerQuantidadeVendida(codigoCompra);
                        
                }
            }
            return 0;      
    }

    public Ferramenta setarProdutoComprado(int codigoCompra) {
       for(Ferramentas fs:this.ferramentasEmEstoque){
                if (codigoCompra==fs.getFerramenta().getCodigo()){
                    return fs.getFerramenta(); 
                }
            }
            return null;     
    }
}