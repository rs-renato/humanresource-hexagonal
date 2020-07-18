package br.com.hrs.api.v1.mapper;

import br.com.hrs.api.v1.resource.JobResource;
import br.com.hrs.core.model.Job;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface JobMapper {

    JobMapper INSTANCE = Mappers.getMapper( JobMapper.class );
    
    Job toModel(JobResource jobResource);

    List<Job> toModelList(List<JobResource> jobResource);

    JobResource toResource(Job job);

    List<JobResource> toResourceList(List<Job> job);

    default <T> T unwrap(Optional<T> optional) {
        return optional.orElse(null);
    }
}