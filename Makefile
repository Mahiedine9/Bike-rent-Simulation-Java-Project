# Makefile for nomPROJET

# Répertoires
SRC_DIR = Vlille/src
CLASSES_DIR = Vlille/src/classes
TEST_DIR = Vlille/test

# Compilateur Java
JC = javac

# Options de compilation
JFLAGS = -g -d $(CLASSES_DIR) -cp $(SRC_DIR)

# Liste des fichiers source
SOURCES = $(wildcard $(SRC_DIR)/vlille/*.java) \
          $(wildcard $(SRC_DIR)/exception/*.java)

# Liste des fichiers de test
TESTS = $(wildcard $(TEST_DIR)/vlille/*.java)

# Nom de la classe principale
MAIN = vlille.SimulationMain

# Cible par défaut
all: run

# Compilation
classes: $(SOURCES)
	$(JC) $(JFLAGS) $(SOURCES)

# Compilation des tests
test-classes: classes $(TESTS)
	javac -cp junit-4.13.2.jar:vlille/classes test/*/*.java

# Exécution
run: classes
	java -cp $(CLASSES_DIR) vlille.SimulationMain

# Exécution des tests
test: test-classes
	java -cp "junit-4.13.2.jar:hamcrest-core-1.3.jar:vlille/test:vlille/classes" org.junit.runner.JUnitCore vlille.*

# Nettoyage
clean:
	rm -rf $(CLASSES_DIR)/*

# Génération du fichier JAR
jar: classes
	jar cvfe Simulation.jar $(MAIN) -C $(CLASSES_DIR) .

.PHONY: all run test clean jar

