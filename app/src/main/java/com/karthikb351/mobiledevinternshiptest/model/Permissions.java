
package com.karthikb351.mobiledevinternshiptest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Permissions {

    @SerializedName("admin")
    @Expose
    private Boolean admin;
    @SerializedName("push")
    @Expose
    private Boolean push;
    @SerializedName("pull")
    @Expose
    private Boolean pull;

    /**
     * @return The admin
     */
    public Boolean getAdmin() {
        return admin;
    }

    /**
     * @param admin The admin
     */
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    /**
     * @return The push
     */
    public Boolean getPush() {
        return push;
    }

    /**
     * @param push The push
     */
    public void setPush(Boolean push) {
        this.push = push;
    }

    /**
     * @return The pull
     */
    public Boolean getPull() {
        return pull;
    }

    /**
     * @param pull The pull
     */
    public void setPull(Boolean pull) {
        this.pull = pull;
    }

}
