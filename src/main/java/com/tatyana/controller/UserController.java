package com.tatyana.controller;

import com.tatyana.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    Parameters parameters;
    File file;
    Experiment experiment;
    User user = new User("mpiuser", "MPIRkfcnth", "MPI USER", "111222", "mpi@mpi.ru", "U", "Y");

    @GetMapping
    public void bla(){

    }

    @RequestMapping(value="/user", method= RequestMethod.POST)
    public void handleFileUpload(@RequestParam("file") MultipartFile file,
                                  @RequestParam("typenode") List<String> typeNodes,
                                  @RequestParam("node") String node,
                                  @RequestParam("process") String process){
        if (!file.isEmpty()) try {
            System.out.println("typenode = " + typeNodes.toString() + ", node = " + node + ", process = " + process);
            List<Node> nodes = new ArrayList<>();
            for (int i = 0; i < typeNodes.size(); i++) {
                switch (typeNodes.get(i)) {
                    case "Orange Pi Win Plus":
                    {
                        nodes.add(new Node("10.0.0.1", 1));
                        break;
                    }
                    case "Orange Pi Prime": {
                        nodes.add(new Node("10.0.0.3", 1));
                        break;
                    }
                    case "Orange Pi PC2": {
                        nodes.add(new Node("10.0.0.2", 1));
                        break;
                    }
                }

            }
            int proc = Integer.parseInt(process);
            parameters = new Parameters(nodes, proc);
            experiment = new Experiment(file, user, parameters);
//            file.transferTo(new File("C:\\ex\\mpi_java.c"));
            file.transferTo(new File("/home/oleg/Desktop/mpi_java.c"));
            createScript();
        } catch (Exception e) {
        }
    }

    public String createScript() {
        String script = "#bin/bash\n" + "echo 'sending source files...'\n";
        String transferFile = "scp -P 50450 /home/oleg/Desktop/mpi_java.c"
                + experiment.getUser().getLogin() + "@80.234.45.88:/home/mpiuser/cloud/\n";
        String loginCluster = "echo 'running a script on cluster...'\n" +
                "ssh " + experiment.getUser().getLogin() + "@80.234.45.88 -p 50450 'bash -s' <<< \\\n" +
                "\t\t\t\t\t\t\t\t\n" +
                "\"cd /home/mpiuser/cloud &&\n" +
                "\t\t\t\t\t\t\t\t\n";
        String compile =  "echo 'compiling sources...' &&\n" +
                "\t\t\t\t\t\t\t\t\n" +
                "mpicc -o ./mpil_java ./mpi_java.c &&\n" +
                "\t\t\t\t\t\t\t\t\n";
        String parameters = "echo 'executing program...' &&\n" +
                "\t\t\t\t\t\t\t\t\n" +
                "mpiexec -n " + experiment.getParameters().getCountProcessors();

        if (experiment.getParameters().getNodes().size() > 1) {
          parameters += " --hosts ";
          for (int i = 0; i < experiment.getParameters().getNodes().size()-1; i++) {
              parameters += experiment.getParameters().getNodes().get(i) + ",";
          }
          parameters += experiment.getParameters().getNodes().get(experiment.getParameters().getNodes().size());
        }

        parameters += " ./mpi_mul &&\n" +
                "\t\t\t\t\t\t\t\t\n" +
                "echo 'execution done.'\"";
        return script + transferFile + loginCluster + compile + parameters;
    }
}
