# Makefile for nomPROJET

# Répertoires
SRC_DIR = Vlille/src
CLASSES_DIR = Vlille/src/classes
TEST_DIR = Vlille/test
JUNIT_JAR = lib/junit-4.13.2.jar


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
	javac -cp $(JUNIT_JAR):$(CLASSES_DIR) $(TEST_DIR)/vlille/*.java

javadoc:
	javadoc -cp $(CLASSES_DIR) Vlille/src/vlille/*.java -d docs

# Exécution
run: classes
	java -cp $(CLASSES_DIR) vlille.SimulationMain



# Exécution des tests
test: test-classes
	java -cp :$(TEST_DIR):$(CLASSES_DIR):$(JUNIT_JAR) org.junit.runner.JUnitCore Vlille/test/vlille.* 
# Nettoyage
clean:
	rm -rf $(CLASSES_DIR)/*

# Génération du fichier JAR
jar: classes
	jar cvfe Simulation.jar $(MAIN) -C $(CLASSES_DIR) .

.PHONY: all run test clean jar

