package com.example.sportnavigator.mapper;


import com.example.sportnavigator.DTO.EncodedImage;
import com.example.sportnavigator.DTO.ReviewDTO;
import com.example.sportnavigator.DTO.SportCourtDTO;
import com.example.sportnavigator.DTO.UserDTO;
import com.example.sportnavigator.Models.*;
import com.example.sportnavigator.Models.Enums.CourtType;
import com.example.sportnavigator.Service.SportCourtService;
import com.example.sportnavigator.Service.UserService;
import com.example.sportnavigator.util.exceptions.UserNotCreatedException;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MapStructMapper {
    private final UserService userService;
    private final SportCourtService sportCourtService;


    public UserDTO userToUserDTO(User user) {
        if (user == null)
            return null;

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        EncodedImage encodedImage = ImageToEncodedImage(user.getImage());
        userDTO.setImage(encodedImage);

        return userDTO;
    }

    public User UserDTOToUser(UserDTO userDTO) {
        if (userDTO == null)
            throw new UserNotCreatedException("Error to create user");

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        UserImage image = EncodedImageToImage(userDTO.getImage(), user);
        user.setImage(image);
        return user;
    }

    public UserImage EncodedImageToImage(EncodedImage encodedImage, User user) {
        UserImage image = new UserImage();

        byte[] data = Base64.decodeBase64(encodedImage.getData());
        image.setBytes(data);
        image.setMime(encodedImage.getMime());
        image.setUser(user);

        return image;
    }

    public CourtImage EncodedImageToCourtImage(EncodedImage encodedImage, SportCourt sportCourt) {
        CourtImage image = new CourtImage();
        byte[] data = Base64.decodeBase64(encodedImage.getData());
        image.setBytes(data);
        image.setMime(encodedImage.getMime());
        image.setSportCourt(sportCourt);
        return image;
    }

    public <T> EncodedImage ImageToEncodedImage(T image) {
        EncodedImage encodedImage = new EncodedImage();
        if (image instanceof UserImage) {
            encodedImage.setMime(((UserImage) image).getMime());
            encodedImage.setData(Base64.encodeBase64String(((UserImage) image).getBytes()));
        } else if (image instanceof CourtImage) {
            encodedImage.setMime(((CourtImage) image).getMime());
            encodedImage.setData(Base64.encodeBase64String(((CourtImage) image).getBytes()));
        }
        return encodedImage;
    }

    public SportCourt SportCourtDTOToSportCourt(SportCourtDTO courtDTO) {
        Set<CourtType> courtType = new HashSet<>();
        Coordinate coordinate = new Coordinate();

        courtType.add(CourtType.valueOf(courtDTO.getCourtType())); //TODO exception handler
        SportCourt court = new SportCourt();
        court.setName(courtDTO.getName());
        court.setDescription(courtDTO.getDescription());
        court.setCourtTypes(courtType);
        court.setUser(userService.getUserById(courtDTO.getUserID()));

        coordinate.setLongitude(courtDTO.getLongitude());
        coordinate.setLatitude(courtDTO.getLatitude());
        coordinate.setSportCourt(court);
        court.setCoordinates(coordinate);
        List<CourtImage> images = new ArrayList<>();
        for (EncodedImage image : courtDTO.getImages()) {
            images.add(EncodedImageToCourtImage(image, court));
        }
        court.setImages(images);
        return court;
    }

    public SportCourtDTO SportCourtToSportCourtDTO(SportCourt sportCourt){
        SportCourtDTO courtDTO = new SportCourtDTO();
        courtDTO.setName(sportCourt.getName());
        courtDTO.setDescription(sportCourt.getDescription());
        courtDTO.setCourtType(sportCourt.getCourtTypes().toString());
        courtDTO.setUserID(sportCourt.getUser().getId());
        courtDTO.setLatitude(sportCourt.getCoordinates().getLatitude());
        courtDTO.setLongitude(sportCourt.getCoordinates().getLongitude());
        List<EncodedImage> images = new ArrayList<>();
        for(CourtImage image: sportCourt.getImages()){
            images.add(ImageToEncodedImage(image));
        }
        courtDTO.setImages(images);
        return courtDTO;
    }

    public Review ReviewDTOToReview(ReviewDTO reviewDTO){
        Review review = new Review();
        review.setDescription(reviewDTO.getDescription());
        review.setRating(reviewDTO.getRating());
        review.setUser(userService.getUserById(reviewDTO.getUserID()));
        review.setSportCourt(sportCourtService.fidById(reviewDTO.getSportCourtID()));

        return review;
    }

    public ReviewDTO ReviewToReviewDTO(Review review){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setDescription(review.getDescription());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setSportCourtID(review.getSportCourt().getId());
        reviewDTO.setUserID(review.getUser().getId());

        return reviewDTO;
    }


}
