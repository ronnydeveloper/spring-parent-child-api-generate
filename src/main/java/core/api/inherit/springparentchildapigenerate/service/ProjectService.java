package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.ProjectDTO;
import core.api.inherit.springparentchildapigenerate.entity.Project;
import id.co.ptap.circleaf.core.dto.ApiResponse;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface ProjectService {

    Project createOrUpdateProject(Project project);

    void deleteProjectById(Long id) throws EntityNotFoundException;

    Project getProjectById(Long id) throws EntityNotFoundException;

    List<ProjectDTO> findAll();

    ApiResponse<List<ProjectDTO>> doView();

    ApiResponse doAdd(Project project);

    ApiResponse doEdit(Project project);

    ApiResponse doDelete(List<Project> projectList);

    ApiResponse doPreview(ProjectDTO projectDTO);

} 