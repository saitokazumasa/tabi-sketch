package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.Place;
import com.tabisketch.bean.form.UpdatePlaceForm;
import com.tabisketch.exception.UpdateFailedException;
import com.tabisketch.mapper.IPlacesMapper;
import com.tabisketch.service.IUpdatePlaceService;
import org.springframework.stereotype.Service;

@Service
public class UpdatePlaceService implements IUpdatePlaceService {
    private final IPlacesMapper placesMapper;

    public UpdatePlaceService(final IPlacesMapper placesMapper) {
        this.placesMapper = placesMapper;
    }

    @Override
    public void execute(final UpdatePlaceForm updatePlaceForm) throws UpdateFailedException {
        final Place place = this.placesMapper.selectById(updatePlaceForm.getId());

        final Place newPlace = new Place(
                updatePlaceForm.getId(),
                place.getGooglePlaceId(),
                updatePlaceForm.getDayId(),
                updatePlaceForm.getBudget(),
                updatePlaceForm.getStartTime(),
                updatePlaceForm.getEndTime(),
                updatePlaceForm.getDesiredStartTime(),
                updatePlaceForm.getDesiredEndTime(),
                updatePlaceForm.getToPolyLine(),
                updatePlaceForm.getToTravelTime(),
                updatePlaceForm.getToTransportation()
        );
        final int result = this.placesMapper.update(newPlace);

        if (result != 1) throw new UpdateFailedException("Placeの更新に失敗しました。");
    }
}
