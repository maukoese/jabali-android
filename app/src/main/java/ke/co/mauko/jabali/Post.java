package ke.co.mauko.jabali;

/**
 * Created by Kevin Barassa on 01-Dec-16.
 */

public class Post {
    private String h_alias, h_avatar, h_by, h_category, h_email, h_tags, h_status, h_created, h_description, h_link;

    //Post constructor
    public Post() {
    }

    public Post(String h_alias, String h_avatar, String h_by,
                  String h_category, String h_email, String h_tags, String h_status,
                  String h_created, String h_description, String h_link) {
        this.h_alias = h_alias;
        this.h_avatar = h_avatar;
        this.h_by = h_by;
        this.h_category = h_category;
        this.h_email = h_email;
        this.h_tags = h_tags;
        this.h_status = h_status;
        this.h_created = h_created;
        this.h_description = h_description;
        this.h_link = h_link;
    }

    public String getName() {
        return h_alias;
    }

    public void setName(String h_alias) {
        this.h_alias = h_alias;
    }

    public String getAvatar() {
        return h_avatar;
    }

    public void setAvatar(String h_avatar) {
        this.h_avatar = h_avatar;
    }

    public String getAuthor() {
        return h_by;
    }

    public void setAuthor(String h_by) {
        this.h_by = h_by;
    }

    public String getCategory() {
        return h_category;
    }

    public void setCategory(String h_category) {
        this.h_category = h_category;
    }

    public String getEmail() {
        return h_email;
    }

    public void setEmail(String h_email) {
        this.h_email = h_email;
    }

    public String getTag() {
        return h_tags;
    }

    public void setTag(String h_tags) {
        this.h_tags = h_tags;
    }

    public String getStatus() {
        return h_status;
    }

    public void setStatus(String h_status) {
        this.h_status = h_status;
    }

    public String getCreatedAt() {
        return h_created;
    }

    public void setCreatedAt(String h_created) {
        this.h_created = h_created;
    }

    public String getBio() {
        return h_description;
    }

    public void setBio(String h_description) {
        this.h_description = h_description;
    }

    public String getWeb() {
        return h_link;
    }

    public void setWeb(String h_link) {
        this.h_link = h_link;
    }


}