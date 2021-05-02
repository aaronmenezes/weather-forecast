
package com.kyser.weatherforecast.model.wallpaper;


public class User {

    private String id;
    private String updatedAt;
    private String username;
    private String name;
    private String firstName;
    private String lastName;
    private String twitterUsername;
    private Object portfolioUrl;
    private Object bio;
    private String location;
    private Links links;
    private ProfileImage profileImage;
    private String instagramUsername;
    private int totalCollections;
    private int totalLikes;
    private int totalPhotos;
    private boolean acceptedTos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public void setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
    }

    public Object getPortfolioUrl() {
        return portfolioUrl;
    }

    public void setPortfolioUrl(Object portfolioUrl) {
        this.portfolioUrl = portfolioUrl;
    }

    public Object getBio() {
        return bio;
    }

    public void setBio(Object bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public String getInstagramUsername() {
        return instagramUsername;
    }

    public void setInstagramUsername(String instagramUsername) {
        this.instagramUsername = instagramUsername;
    }

    public int getTotalCollections() {
        return totalCollections;
    }

    public void setTotalCollections(int totalCollections) {
        this.totalCollections = totalCollections;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(int totalLikes) {
        this.totalLikes = totalLikes;
    }

    public int getTotalPhotos() {
        return totalPhotos;
    }

    public void setTotalPhotos(int totalPhotos) {
        this.totalPhotos = totalPhotos;
    }

    public boolean isAcceptedTos() {
        return acceptedTos;
    }

    public void setAcceptedTos(boolean acceptedTos) {
        this.acceptedTos = acceptedTos;
    }

}
