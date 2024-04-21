package com.tsfn.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tsfn.model.Instagram;
import com.tsfn.model.LoaderDTO;
@Configuration
public class ModelMapperConfig {

    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        
        // Mapping from Instagram to LoaderDTO
        modelMapper.addMappings(new PropertyMap<Instagram, LoaderDTO>() {
            @Override
            protected void configure() {
                skip(destination.getId()); // Exclude mapping for id
                map().setPostId(source.getPostId());
                map().setContentType(source.getPostType());
                map().setImpressions(source.getImpressions());
                map().setViews(source.getReach());
                map().setClicks(source.getSaves());
                map().setLikes(source.getLikes()); 
                map().setComments(source.getComments());
                map().setShares(source.getShares());
               // map().setTimestamp(source.getPublishTime());
                
            }
        });

        // Post-mapping operations
        modelMapper.getTypeMap(Instagram.class, LoaderDTO.class).setPostConverter(context -> {
            Instagram source = context.getSource();
            LoaderDTO destination = context.getDestination();
            if (source.getImpressions() > 0 && source.getSaves() > 0) {
                destination.setCTR(source.getSaves() / (double) source.getImpressions());
            } else {
                destination.setCTR(0);
            }
            if (source.getReach() > 0) {
                destination.setEngagementrate(source.getLikes() / (double) source.getReach());
            } else {
                destination.setEngagementrate(0);
            }
            return destination;
        });

        return modelMapper;
    }
}
