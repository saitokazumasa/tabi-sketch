package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.GooglePlace;
import com.tabisketch.bean.entity.Place;
import com.tabisketch.bean.form.CreatePlaceForm;
import com.tabisketch.exception.InsertFailedException;
import com.tabisketch.mapper.IGooglePlaceMapper;
import com.tabisketch.mapper.IPlacesMapper;
import com.tabisketch.service.ICreatePlaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreatePlaceService implements ICreatePlaceService {
    private final IPlacesMapper placesMapper;
    private final IGooglePlaceMapper googlePlaceMapper;

    public CreatePlaceService(
            final IPlacesMapper placesMapper,
            final IGooglePlaceMapper googlePlaceMapper
    ) {
        this.placesMapper = placesMapper;
        this.googlePlaceMapper = googlePlaceMapper;
    }

    @Override
    @Transactional
    public void execute(final CreatePlaceForm createPlaceForm) throws InsertFailedException {
        final GooglePlace selectedGooglePlace =
                this.googlePlaceMapper.selectByPlaceId(createPlaceForm.getGooglePlaceId());

        if (selectedGooglePlace != null) {
            final var place = Place.generate(
                    selectedGooglePlace.getId(),
                    createPlaceForm.getDayId(),
                    createPlaceForm.getBudget(),
                    createPlaceForm.getStartTime(),
                    createPlaceForm.getEndTime(),
                    createPlaceForm.getDesiredStartTime(),
                    createPlaceForm.getDesiredEndTime(),
                    createPlaceForm.getToPolyLine(),
                    createPlaceForm.getToTravelTime(),
                    createPlaceForm.getToTransportation()
            );
            final int result = this.placesMapper.insert(place);

            if (result != 1) throw new InsertFailedException("Placeの追加に失敗しました。");
            return;
        }

        final GooglePlace googlePlace = GooglePlace.generate(
                createPlaceForm.getGooglePlaceId(),
                createPlaceForm.getName(),
                createPlaceForm.getLatitude(),
                createPlaceForm.getLongitude()
        );
        final int googlePlaceResult = this.googlePlaceMapper.insert(googlePlace);

        final var place = Place.generate(
                googlePlace.getId(),
                createPlaceForm.getDayId(),
                createPlaceForm.getBudget(),
                createPlaceForm.getStartTime(),
                createPlaceForm.getEndTime(),
                createPlaceForm.getDesiredStartTime(),
                createPlaceForm.getDesiredEndTime(),
                createPlaceForm.getToPolyLine(),
                createPlaceForm.getToTravelTime(),
                createPlaceForm.getToTransportation()
        );
        final int placeResult = this.placesMapper.insert(place);

        if (googlePlaceResult != 1) throw new InsertFailedException("GooglePlaceの追加に失敗しました。");
        if (placeResult != 1) throw new InsertFailedException("Placeの追加に失敗しました。");
    }
}
