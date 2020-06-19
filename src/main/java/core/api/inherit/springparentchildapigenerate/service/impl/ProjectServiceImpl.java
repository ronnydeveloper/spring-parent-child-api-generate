package core.api.inherit.springparentchildapigenerate.service.impl;

import core.api.inherit.springparentchildapigenerate.dto.ProjectDTO;
import core.api.inherit.springparentchildapigenerate.entity.Project;
import core.api.inherit.springparentchildapigenerate.repository.ProjectRepo;
import core.api.inherit.springparentchildapigenerate.service.ProjectService;
import id.co.ptap.circleaf.core.dto.ApiResponse;
import id.co.ptap.circleaf.core.enums.ResponseCode;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    static final Logger logger = Logger.getLogger(ProjectServiceImpl.class);

    @Autowired
    private ProjectRepo projectRepository;

    @Override
    public Project createOrUpdateProject(Project project) {
         Optional<Project> projectOptional = projectRepository.findById(project.getId());
         if(projectOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             Project newProject = modelMapper.map(project, Project.class);
             newProject = projectRepository.save(newProject);
             return newProject;
         } else {
             project = projectRepository.save(project);
             return project;
         }
    }

    @Override
    public void deleteProjectById(Long id) throws EntityNotFoundException {
         Optional<Project> projectOptional = projectRepository.findById(id);
         if(projectOptional.isPresent())
         {
            projectRepository.deleteById(id);
         } else {
            throw new EntityNotFoundException("No Project record exist for given id");
         }
    }

    @Override
    public Project getProjectById(Long id) throws EntityNotFoundException {
         Optional<Project> projectOptional = projectRepository.findById(id);
         if(projectOptional.isPresent())
         {
            return projectOptional.get();
         } else {
            throw new EntityNotFoundException("No Project record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<ProjectDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<ProjectDTO> projectList = this.findAll();
             apiResponse.setData(projectList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(Project project) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (project == null) {
                throw new NullPointerException("Project cannot be null");
            }
            else {
                long max = 0;
                long count = projectRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = projectRepository.max();
                }
                project.setId(max);
            }
            Optional<Project> projectOptional = projectRepository.findById(project.getId());
            if(projectOptional.isPresent()) {
                throw new EntityExistsException("There is a Project record exist for given id");
            } else {
                this.createOrUpdateProject(project);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(Project project) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (project == null) {
                throw new NullPointerException("project cannot be null");
            }
            this.createOrUpdateProject(project);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<Project> projectList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (projectList.size() < 1) {
                throw new NullPointerException("project cannot be null");
            }
            for (Project obj : projectList) {
                this.deleteProjectById(obj.getId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(ProjectDTO projectDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getProjectById(projectDTO.getId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<ProjectDTO> findAll() {
        List<ProjectDTO> newList = new ArrayList<>();
        for(Project p : projectRepository.findAll()) {
             ProjectDTO projectDTO = ProjectDTO.builder()
                     .id(p.getId())
                     .name(p.getName())
                     .partnerID(p.getPartnerID())
                     .noProject(p.getNoProject())
                     .noKontrak(p.getNoKontrak())
                     .dateStart(p.getDateStart())
                     .dateEnd(p.getDateEnd())
                     .statusBA(p.getStatusBA())
                     .tglKontrak(p.getTglKontrak())
                     .tglPerjanjianFrom(p.getTglPerjanjianFrom())
                     .tglPerjanjianTo(p.getTglPerjanjianTo())
                     .projectStatus(p.getProjectStatus())
                     .parent(p.getParent())
                     .companyID(p.getCompanyID()).build();
             newList.add(projectDTO);
        }
        return newList;
    }

} 