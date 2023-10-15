package br.com.mvteus.todolist.task;

import br.com.mvteus.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) { // Pegamos o idUser repassado pelo filtro da classe FilterTaskAuth
        var idUser = request.getAttribute("idUser"); // Pegamos o idUser da requisição (request)
        taskModel.setId((UUID) idUser); // Setamos o idUser no taskModel

        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início/término deve ser maior do que a data atual");
        }

        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início deve ser menor do que a data de término");
        }

        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    /*
     * Listar tarefas
     * */
    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }

    /*
     * Atualizar tarefas
     * */
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) {
        /*
         * Usamos a variável ID do path/rota para sabermos qual task atualizar
         * */

        var task = this.taskRepository.findById(id).orElse(null);
        Utils.copyNonNullProperties(taskModel, task);

        /*
        * Validar se a tarefa é nula
        * */
        if (task == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Tarefa não encontrada.");
        }

        var idUser = request.getAttribute("idUser");

        /*
        * Se existir a tarefa...
        * Validar se o usuário que está tentando alterar, é proprietário daquela tarefa
        * */
        if (!task.getIdUser().equals(idUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Usuário não tem permissão para alterar essa tarefa");
        }

        /*
         * var idUser = request.getAttribute("idUser");
         * taskModel.setIdUser((UUID) idUser);
         * taskModel.setId(id);
         */
        var taskUpdated = this.taskRepository.save(task);
        return ResponseEntity.ok().body(this.taskRepository.save(taskUpdated));
    }
}
