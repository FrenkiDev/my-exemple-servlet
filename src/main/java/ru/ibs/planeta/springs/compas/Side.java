package ru.ibs.planeta.springs.compas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.Range;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Side {
    private String north;
    private String south;
    private String west;
    private String east;

    private String northeast;
    private String northwest;
    private String southeast;
    private String southwest;

    private Degree degree;

    public Degree getDegree(int range) {
        degree = new Degree();
        if(getSideByRange(north, range)){
            degree.setSide("North");
        }else if(getSideByRange(south, range)){
            degree.setSide("South");
        }else if(getSideByRange(west, range)){
            degree.setSide("West");
        }else if(getSideByRange(east, range)){
            degree.setSide("East");
        }else if(getSideByRange(northeast, range)){
            degree.setSide("NorthEast");
        }else if(getSideByRange(northwest, range)){
            degree.setSide("NorthWest");
        }else if(getSideByRange(southeast, range)){
            degree.setSide("SouthEast");
        }else if(getSideByRange(southwest, range)){
            degree.setSide("SouthWest");
        }

        return degree;
    }

    public boolean getSideByRange(String strRang, int range) {
        int x = Integer.parseInt(strRang.split("-")[0]);
        int y = Integer.parseInt(strRang.split("-")[1]);

        Range<Integer> myRange = Range.between(x, y);
        if (myRange.contains(range)) {
            return true;
        } else
            return false;
    }

    @Setter
    @Getter
    public class Degree {
        private String Side;
    }
}
