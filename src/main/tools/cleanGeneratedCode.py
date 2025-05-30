
# Script to clean the source code generated by the hibernate tools plugin
# Author G. SMITS
# Date September 2023
# Usage : python src/main/tools/cleanGeneratedCode.py -m src/main/java/fr/atlantique/imt/inf211/jobmngt/model -d src/main/java/fr/atlantique/imt/inf211/jobmngt/dao



##### IMPORT
from typing import List, Dict, Callable, Any
import sys
import getopt
from glob import glob
import os
import time

def modify_entities(sourced : str, entityd : str):
    """_summary_

    Args:
        entityd (str): path of the directory storing the generated (entities)
        repositoriesd (str): path of the directory storing the generated repositories - NOT USED IN THIS FUNCTION
    """
    print(f"############ PHASE: modify_entities ############")
    print(f"\t- applied on %s"%(entityd))
    pathtofiles=(sourced+"/*.java").strip()
    for javaf in glob(pathtofiles):
        if "Home" not in javaf:
            print(f"\t\t ### PROCESS FILE %s"%(javaf))
            javacode : str = ""
            with open(javaf, "r") as j_file:
                javacode = j_file.read()
                javacode = javacode.replace("// default package" ,"package fr.atlantique.imt.inf211.jobmngt.entity;")
                #From javax to jakarta
                javacode = javacode.replace("import javax", "import jakarta")
                #Systematically declar autoin@cremented primary keys
                tabup = os.path.basename(javaf)[:-5].upper()
                seq = f"\t@SequenceGenerator(name = \"%s_ID_GENERATOR\", sequenceName = \"%s_ID_SEQ\",allocationSize=1)"%(tabup,tabup)
                gen = f"\t@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = \"%s_ID_GENERATOR\")"%(tabup)
                json = f"@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = \"id\")"
                javacode = javacode.replace("@Id", f"@Id\n%s\n%s"%(seq, gen))
                javacode = javacode.replace("public class ", f"{json}\npublic class ")
                
                fstimportidx = javacode.index("import");
                theimports = "import jakarta.persistence.GenerationType;\nimport jakarta.persistence.SequenceGenerator;\n import jakarta.persistence.GeneratedValue;"
                javacode = javacode[:fstimportidx] + theimports + javacode[fstimportidx:]
            os.remove(javaf)
                #add import jakarta.persistence.SequenceGenerator and jakarta.persistence.GeneratedValue
            with open(os.path.join(entityd,os.path.basename(javaf)).strip(), "w") as j_file:
                j_file.write(javacode)
        
    print(f"############ modify_entities DONE ############")

def modify_repositories(sourced : str,  daod : str):
    """_summary_

    Args:
        entityd (str): path of the directory storing the generated (entities) - NOT USED IN THIS FUNCTION
        repositoriesd (str): path of the directory storing the generated repositories
    """
    print(f"############ PHASE: modify_repositories ############")
    print(f"\t- applied on %s"%(daod))
    pathtofiles=(sourced+"/*Home.java").strip()
 
    for javaf in glob(pathtofiles):
        print(f"\t\t ### PROCESS FILE %s"%(javaf))
        fname = os.path.basename(javaf)[:-5]
        
        javacode : str = ""
        with open(javaf, "r") as j_file:
            javacode = j_file.read()
            javacode = javacode.replace("// default package" ,"package fr.atlantique.imt.inf211.jobmngt.dao;")
            javacode = javacode.replace(fname, fname.replace("Home","Dao"))
            javacode = javacode.replace("import javax", "import jakarta")
            javacode = javacode.replace("@Stateless", "@Repository")
            javacode = javacode.replace("import jakarta.ejb.Stateless;", "import org.springframework.stereotype.Repository;")
            #add import entitues
            fstimportidx = javacode.index("import");
            importEntities = "import fr.atlantique.imt.inf211.jobmngt.entity.*;"
            javacode = javacode[:fstimportidx] + importEntities + javacode[fstimportidx:]
        os.remove(javaf)

        with open(os.path.join(daod,os.path.basename(javaf)).strip().replace("Home","Dao"), "w") as j_file:
            j_file.write(javacode)

        #os.rename(javaf,javaf.replace("Home.java","Repository.java"))
    print(f"############ modify_repositories DONE ############")

if __name__ == "__main__":
    # call example : python src/main/tools/cleanGeneratedCode.py -s src/main/java/ -e src/main/java/fr/atlantique/imt/inf211/jobmngt/entity -r src/main/java/fr/atlantique/imt/inf211/jobmngt/repository
    process_steps : List[Callable[[str, str], Any]] = [modify_entities, modify_repositories]

    # Manage the parameters for the path of the entity and dao directories
    argv = sys.argv[1:]
    source_directory= "src/main/java/"
    entity_directory = "src/main/java/fr/atlantique/imt/inf211/jobmngt/entity/"
    repository_directory="src/main/java/fr/atlantique/imt/inf211/jobmngt/dao/"
    try:
        opts, args = getopt.getopt(argv, "e:r:")
        for opt, arg in opts:
            if opt in ['-s']:
                source_directory = arg
            if opt in ['-e']:
                entity_directory = arg
            elif opt in ['-r']:
                repository_directory = arg
    except Exception as e:
        print("Error parsing the parameter:",e)

    #modify generated entity classes
    modify_entities(source_directory, entity_directory)
    #modify generated dao classes
    modify_repositories(source_directory,repository_directory)
    