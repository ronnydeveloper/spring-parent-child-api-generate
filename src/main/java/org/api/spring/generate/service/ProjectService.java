package org.api.spring.generate.service;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.Project;
import org.api.spring.generate.dto.ProjectDTO;

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