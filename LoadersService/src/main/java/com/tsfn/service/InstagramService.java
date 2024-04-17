package com.tsfn.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsfn.dto.LoaderDTO;
import com.tsfn.model.Instagram;
import com.tsfn.repository.InstagramRepo;

@Service
public class InstagramService {

	@Autowired
	private InstagramRepo instagramRepo;
	@Autowired
	private ModelMapper modelMapper;


	private LoaderDTO convertEntityToDto(Instagram instagram){
		modelMapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.LOOSE);
		LoaderDTO instagramDTO = new LoaderDTO();
		instagramDTO = modelMapper.map(instagram, LoaderDTO.class);
		return instagramDTO;
	}

	private Instagram convertDtoToEntity(LoaderDTO loaderDTO) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Instagram instagram = modelMapper.map(loaderDTO, Instagram.class);
		return instagram;
	}






	// Create
	public LoaderDTO createInstagramEntityData(LoaderDTO instagramData) {
		Instagram instagramEntity = convertDtoToEntity(instagramData);
		Instagram savedEntity = instagramRepo.save(instagramEntity);
		return convertEntityToDto(savedEntity);
	}

	// Read
	public List<LoaderDTO> getAllInstagramData() {
		return instagramRepo.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}


	public Optional<LoaderDTO> getInstagramDataById(int postId) {
		Optional<Instagram> instagramData = instagramRepo.findById(postId);
		return instagramData.map(this::convertEntityToDto);
	}

	// Update
	public LoaderDTO updateInstagramData(int postId, LoaderDTO updatedData) {
		Optional<Instagram> existingData = instagramRepo.findById(postId);
		if (existingData.isPresent()) {
			Instagram dataToUpdate = existingData.get();

			if (updatedData.getTimestamp() != null) {
				dataToUpdate.setTimestamp(updatedData.getTimestamp());
			}
			if (updatedData.getContentType() != null) {
				dataToUpdate.setContentType(updatedData.getContentType());
			}
			if (updatedData.getImpressions() != 0) {
				dataToUpdate.setImpressions(updatedData.getImpressions());
			}
			if (updatedData.getViews() != 0) {
				dataToUpdate.setViews(updatedData.getViews());
			}
			if (updatedData.getClicks() != 0) {
				dataToUpdate.setClicks(updatedData.getClicks());
			}
			if (updatedData.getLikes() != 0) {
				dataToUpdate.setLikes(updatedData.getLikes());
			}
			if (updatedData.getComments() != 0) {
				dataToUpdate.setComments(updatedData.getComments());
			}
			if (updatedData.getShares() != 0) {
				dataToUpdate.setShares(updatedData.getShares());
			}
			if (updatedData.getEngagementrate() != 0) {
				dataToUpdate.setEngagementrate(updatedData.getEngagementrate());
			}
			if (updatedData.getCTR() != 0) {
				dataToUpdate.setCTR(updatedData.getCTR());
			}
			dataToUpdate.setPostId(postId);


			Instagram updatedEntity = instagramRepo.save(dataToUpdate);

			return convertEntityToDto(updatedEntity);
		} else {
			return null;
		}
	}


	// Delete
	public void deleteInstagramData(int postId) {
		instagramRepo.deleteById(postId);
	}
}

