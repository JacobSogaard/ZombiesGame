package group8.common.data;

import group8.common.data.entityparts.EntityPart;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();
    private EntityType type;

    private float[] shapeX = new float[4];
    private float[] shapeY = new float[4];
    private float radius, height, width;
    private Map<Class, EntityPart> parts;
    private String imagePath;
    private float height = 0, width = 0;
    
    public Entity() {
        parts = new ConcurrentHashMap<>();
    }
    
    public void add(EntityPart part) {
        parts.put(part.getClass(), part);
    }
    
    public void remove(Class partClass) {
        parts.remove(partClass);
    }
    
    public <E extends EntityPart> E getPart(Class partClass) {
        return (E) parts.get(partClass);
    }
    
    public void setRadius(float r){
        this.radius = r;
    }
    
    public float getRadius(){
        return radius;
    }

    public String getID() {
        return ID.toString();
    }

    public float[] getShapeX() {
        return shapeX;
    }

    public void setShapeX(float[] shapeX) {
        this.shapeX = shapeX;
        if (this.height == 0) {
            this.setHeight();
            this.setWidth();
        }
    }

    public float[] getShapeY() {
        return shapeY;
    }

    public void setShapeY(float[] shapeY) {
        this.shapeY = shapeY;
    }
    
    private void setHeight() {
        this.height = this.shapeY[1] - this.shapeY[0];
        System.out.println("Højde: " + this.height);
    }
    
    private void setWidth() {
        this.width = this.shapeX[3] - this.shapeX[0];
        System.out.println("Bredde: " + this.width);
    }

    /**
     * @return the imagePath
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath the imagePath to set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * @return the type
     */
    public EntityType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(EntityType type) {
        this.type = type;
    }
<<<<<<< HEAD
    
    public float getHeight() {
        return this.height;
    }
    
    public float getWidth() {
        return this.width;
=======

    /**
     * @return the height
     */
    public float getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * @return the width
     */
    public float getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(float width) {
        this.width = width;
>>>>>>> 809384f3784ea950ad1b9b5d401e5d362c1c9d3f
    }
}
