package com.wajahat.workersapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.wajahat.workersapp.Constants;

import java.util.List;

public class Worker implements Parcelable {

    public static final int TYPE_HEADER = 1;
    public static final int TYPE_ITEM = 2;
    private String mRoleType;
    private int itemType = TYPE_ITEM;
    private Attribute attributes;

    public int getItemType() {
        return itemType;
    }

    public String getRoleType() {
        return mRoleType;
    }

    public Worker(String roleType) {
        mRoleType = roleType;
        itemType = TYPE_HEADER;
    }

    protected Worker(Parcel in) {
        mRoleType = in.readString();
        itemType = in.readInt();
        attributes = in.readParcelable(Attribute.class.getClassLoader());
    }

    public static final Creator<Worker> CREATOR = new Creator<Worker>() {
        @Override
        public Worker createFromParcel(Parcel in) {
            return new Worker(in);
        }

        @Override
        public Worker[] newArray(int size) {
            return new Worker[size];
        }
    };

    public Attribute getAttributes() {
        return attributes;
    }

    public void setAttributes(Attribute attributes) {
        this.attributes = attributes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mRoleType);
        dest.writeInt(itemType);
        dest.writeParcelable(attributes, flags);
    }

    public static class Attribute implements Parcelable {

        @SerializedName(Constants.FULL_NAME)
        String fullName;
        String role;
        String contractor;
        @SerializedName(Constants.HELMET_COLOR)
        String helmetColor;
        List<Inventory> Inventories;

        public String getFullName() {
            return fullName;
        }

        public String getRole() {
            return role;
        }

        public String getContractor() {
            return contractor;
        }

        public String getHelmetColor() {
            return helmetColor;
        }

        public List<Inventory> getInventories() {
            return Inventories;
        }

        protected Attribute(Parcel in) {
            fullName = in.readString();
            role = in.readString();
            contractor = in.readString();
            helmetColor = in.readString();
            Inventories = in.createTypedArrayList(Inventory.CREATOR);
        }

        public static final Creator<Attribute> CREATOR = new Creator<Attribute>() {
            @Override
            public Attribute createFromParcel(Parcel in) {
                return new Attribute(in);
            }

            @Override
            public Attribute[] newArray(int size) {
                return new Attribute[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(fullName);
            dest.writeString(role);
            dest.writeString(contractor);
            dest.writeString(helmetColor);
            dest.writeTypedList(Inventories);
        }

        public static class Inventory implements Parcelable {

            @SerializedName(Constants.ATTRIBUTES)
            InventoryAttribute inventoryAttribute;

            public InventoryAttribute getInventoryAttribute() {
                return inventoryAttribute;
            }

            Inventory(Parcel in) {
                inventoryAttribute = in.readParcelable(InventoryAttribute.class.getClassLoader());
            }

            public static final Creator<Inventory> CREATOR = new Creator<Inventory>() {
                @Override
                public Inventory createFromParcel(Parcel in) {
                    return new Inventory(in);
                }

                @Override
                public Inventory[] newArray(int size) {
                    return new Inventory[size];
                }
            };

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(inventoryAttribute, flags);
            }

            public static class InventoryAttribute implements Parcelable {

                @SerializedName(Constants.LAST_SEEN)
                String lastSeen;

                public String getLastSeen() {
                    return lastSeen;
                }

                protected InventoryAttribute(Parcel in) {
                    lastSeen = in.readString();
                }

                public static final Creator<InventoryAttribute> CREATOR = new Creator<InventoryAttribute>() {
                    @Override
                    public InventoryAttribute createFromParcel(Parcel in) {
                        return new InventoryAttribute(in);
                    }

                    @Override
                    public InventoryAttribute[] newArray(int size) {
                        return new InventoryAttribute[size];
                    }
                };

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(lastSeen);
                }
            }
        }
    }
}