# Nome do compilador
JAVAC = javac

# Nome do interpretador
JAVA = java

# Diretório de saída para os arquivos .class
OUT_DIR = bin

# Arquivos fonte
SOURCES = Main.java Livro.java Arvore.java No.java

# Arquivos .class
CLASSES = $(SOURCES:.java=.class)

# Alvo padrão
all: $(CLASSES)

# Regra para compilar arquivos .java em .class
%.class: %.java
	$(JAVAC) -d $(OUT_DIR) $<

# Alvo para executar o programa
run: all
	$(JAVA) -cp $(OUT_DIR) Main

# Alvo para limpar os arquivos compilados
clean:
	rm -rf $(OUT_DIR)/*.class

# Cria o diretório de saída se não existir
$(shell mkdir -p $(OUT_DIR))