package br.edu.fatecguarulhos.caixaeletronico;

public class CaixaEletronico implements ICaixaEletronico{
    private int totalDisponivel;
    private int cotaMinima;
    private String extrato = "";

	private int[][] cedulas = {{100,100}, {50,200}, {20,300},{10,350},{5,450}, {2,500}};
	
	public CaixaEletronico() {
	}
	public String pegaRelatorioCedulas() {
		//logica que pega a quantidade de notas das respectivas cedulas e as printa
	    String resposta = "Relatório de Cédulas:\n";

	    for (int i = 0; i < cedulas.length; i++) {
	        int valor = cedulas[i][0];
	        int quantidade = cedulas[i][1];

	        resposta += "Nota de R$" + valor + ": " + quantidade + " Cédulas disponíveis\n";
	    }

	    return resposta;
	}
		
		public String pegaValorTotalDisponivel() {
			atualizarTotalDisponivel();
			String resposta = ("Valor total disponivel : R$"+totalDisponivel+",00");
			//logica de pega o valor total disponivel no caixa eletronio
			return resposta;
		}
		
		public String reposicaoCedulas(Integer cedula, Integer quantidade) {
				
			if (quantidade <= 0) {
				throw new RuntimeException("Por favor ensira uma quantidade acima de 0");
			}else {
				//logiva de adicionar a quantidade de notas a respectivas cedulas
					if (cedula == 100) {
						cedulas[0][1] = cedulas[0][1] += quantidade; 
					}
					else if (cedula == 50) {
						cedulas[1][1] = cedulas[1][1] += quantidade; 
					}
					else if (cedula == 20) {
						cedulas[2][1] = cedulas[2][1] += quantidade;
					}
					else if (cedula == 10) {
						cedulas[3][1] = cedulas[3][1] += quantidade; 
					}
					else if (cedula == 5) {
						cedulas[4][1] = cedulas[4][1] += quantidade; 
					}
					else if (cedula == 2) {
						cedulas[5][1] = cedulas[5][1] += quantidade; 
					}
					else{
						 throw new RuntimeException("A cedula digitada não existe no sistema.");
					}
			}				
				String resposta = "Reposição realizada com sucesso";
				return resposta;
			}
		
		public String sacar(Integer valor){
			int valorRestante = valor;
			int[][] cedulasSacadas = {{100,0},{50,0},{20,0},{10,0}, {5,0},{2,0}};
			int numeroDeCedulasEmitidas = 0;
			String resposta = "";
			//if(valor > cotaMinima) 
			//	throw new RuntimeException("Cota mínima de R$" + cotaMinima+" atingida");
			if(valor < 2 || valor == 3)
				throw new RuntimeException("Não é possível realizar esse tipo de saque");
			for(int i = 0; i < cedulas.length; i++) {
				//Recalcular notas se o valor não puder ser sacado com as maiores notas
				
				cedulasSacadas[i][1] = calcularQuantidadeCedulas(valorRestante, cedulas[i][0]);
				
				boolean naoHaCedulasSuficientes = cedulasSacadas[i][1] > cedulas[i][1];
				//Cédulas necessárias serão as que restaram no caixa.
				if(naoHaCedulasSuficientes) 
					cedulasSacadas[i][1] = cedulas[i][1];
				
				int valorSacado = cedulasSacadas[i][0] * cedulasSacadas[i][1];
				valorRestante -= valorSacado;
				numeroDeCedulasEmitidas +=  cedulasSacadas[i][1];
				if(valorRestante == 1 || valorRestante == 3) {
					cedulasSacadas[i][1]--; 
					valorRestante += cedulas[i][0];
					numeroDeCedulasEmitidas--;
				}
			}
			if(valorRestante != 0) 
				throw new RuntimeException("Não há notas suficientes para sacar este valor.");
			if(numeroDeCedulasEmitidas > 30) {
				throw new RuntimeException("Não é possível sacar mais de 30 cédulas");
			}
			removerCedulas(cedulasSacadas);
			for(int nota[] : cedulasSacadas) {
				String substantivo = nota[1] == 1 ? " nota" : " notas";
				if(nota[1] != 0)
				resposta = resposta.concat(nota[1] + substantivo+" de R$"+ nota[0] + "\n");
			}
			
			registrarSaque(valor, resposta);  //Registra automaticamente o saque no extrato
			
			return resposta;
		}
		private int calcularQuantidadeCedulas(int valorRestante, int valorCedula) {
			return (valorRestante - (valorRestante % valorCedula)) / valorCedula;
		}
		private void devolverCedulas(int cedulas[][]) {
			for(int i = 0; i < cedulas.length; i++) {
				this.cedulas[i][1] += cedulas[i][1];
			}
		}
		private void removerCedulas(int cedulas[][]) {
			for(int i = 0; i < cedulas.length; i++) {
				this.cedulas[i][1] -= cedulas[i][1];
			}
		}
		public String armazenaCotaMinima(Integer minimo) {
            cotaMinima = minimo;
		    String resposta = "Cota mínima atual de R$" + minimo;
		    //logica de armazenar a cota minima para saque e criar um //mensagem(resposta)ao usuario
		    return resposta;
		}

        public void verificarCotaMinima() {
            atualizarTotalDisponivel();
            if(cotaMinima > totalDisponivel)
                throw new RuntimeException("Caixa vazio: Chame o operador.");
        }

        private void atualizarTotalDisponivel() {
        	totalDisponivel = 0;
            for(int[] cedula : cedulas) {
                // para cada cedula, adicionar o resultado do tipo(2, 5, 10 reais...) x quantidade
                totalDisponivel += cedula[0] *  cedula[1];
            }
        }
        
        //Método responsável pelos registros de saques
        public void registrarSaque(Integer valor, String resultado) {
        	//zerar o total antes de recalcular, evitando erro de acumulação errada
        	totalDisponivel = 0;  
        	atualizarTotalDisponivel();  
        	
        	// Adiciona informações do saque ao extrato
        	extrato += "Saque de R$ " + valor + "\n";  // valor sacado
        	extrato += resultado;  // notas utilizadas
        	extrato += "Saldo restante: R$ "+ totalDisponivel + "\n\n"; // saldo após o sque
        }
        
        //Método responsável por gerar o extrato ao clicar no botão sair
        public String gerarExtratoFinal() {
        	
        	//zerar o total antes de recalcular, evitando erro de acumulação errada
        	totalDisponivel = 0;
        	atualizarTotalDisponivel();
        
        	// Lógica para o caso de nenhum saque ter sido realizado
        	if(extrato.isEmpty()) {
        		return "Nenhum saque realizado.\nSaldo atual: R$ "+totalDisponivel;
        	}
        	
        	// Irá retornar o extrato completo com o saldo final
        	return "===== EXTRATO =====\n\n" + extrato + "Saldo final: R$ "+totalDisponivel;
        } 

}
