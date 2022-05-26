package entity;

import java.util.Objects;

public class Plane {

        private String registerno;
        private String model;
        private String columnNum;
        private String maxPsgr;

        public void setRegisterno(String s) { this.registerno = s; }

        public String getRegisterno() { return registerno; }

        public void setModel(String s) { this.model = s; }

        public String getModel() { return model; }

        public void setColumnNum(String i) { columnNum = i; }

        public String getColumnNum() { return columnNum; }

        public void setMaxPsgr(String i) { maxPsgr = i; }

        public String getMaxPsgr() { return maxPsgr; }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Plane plane = (Plane) o;
                return Objects.equals(registerno, plane.registerno) && Objects.equals(model, plane.model) && Objects.equals(columnNum, plane.columnNum) && Objects.equals(maxPsgr, plane.maxPsgr);
        }

        @Override
        public int hashCode() {
                return Objects.hash(registerno, model, columnNum, maxPsgr);
        }
}
