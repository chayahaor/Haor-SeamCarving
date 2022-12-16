import java.awt.*;

public class Pixel {
    private Color color;
    private double cellEnergy;
    private double energyH;
    private double energyV;

    public Pixel(int intColor) {
        this.color = new Color(intColor);
        this.cellEnergy = 0;
        this.energyH = 0;
        this.energyV = 0;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getCellEnergy() {
        return cellEnergy;
    }

    public void setCellEnergy(double cellEnergy) {
        this.cellEnergy = cellEnergy;
    }

    public double getEnergyH() {
        return energyH;
    }

    public void setEnergyH(double energyH) {
        this.energyH = energyH;
    }

    public double getEnergyV() {
        return energyV;
    }

    public void setEnergyV(double energyV) {
        this.energyV = energyV;
    }


}

