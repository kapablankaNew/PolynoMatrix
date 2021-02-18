package kapablankaNew.PolynoMatrix;

import java.util.*;

public class Polynom {
    final static int PLUS = 0;
    final static int MINUS = 1;

    List<Double> coefficients;
    List<List<String>> parameters;

    public Polynom() {
        coefficients = new ArrayList<>();
        parameters = new ArrayList<>();
    }

    public Polynom(String expression){
        coefficients = new ArrayList<>();
        parameters = new ArrayList<>();
        String data = expression.replaceAll("[\n\t\r ]", "");
        String[] addends;
        addends = data.split("\\+");
        for (String addend : addends){
            String[] member = addend.split("-");
            for (int i = 0; i < member.length; i++){
                if (i == 0){
                    addMember(member[i], Polynom.PLUS);
                } else {
                    addMember(member[i], Polynom.MINUS);
                }
            }
        }
        simplify();
    }

    private void addMember(String expression, int sign){
        if (expression.equals("")){
            return;
        }
        String[] multipliers = expression.split("\\*");
        List<String> params = new ArrayList<>();
        double coeff = 1;
        for (String multiplier : multipliers){
            if(multiplier.matches("[0-9.-]+")){
                coeff *= Double.parseDouble(multiplier);
            } else {
                params.add(multiplier);
            }
        }
        if (sign == Polynom.MINUS) {
            coeff = -coeff;
        }
        coefficients.add(coeff);
        Collections.sort(params);
        parameters.add(params);
    }

    public void simplify(){
        List<Double> newCoefficients = new ArrayList<>();
        List<List<String>> newParameters = new ArrayList<>();

        for (int i = 0; i < coefficients.size(); i++) {
            double coeff = coefficients.get(i);
            List<String> parameter = parameters.get(i);
            Collections.sort(parameter);
            if(! newParameters.contains(parameter)){
                for (int j = i + 1; j < coefficients.size(); j++) {
                    List<String> secondParameter = parameters.get(j);
                    Collections.sort(secondParameter);
                    if (secondParameter.equals(parameter)) {
                        coeff += coefficients.get(j);
                    }
                }
                newCoefficients.add(coeff);
                newParameters.add(parameter);
            }
        }

        ListIterator<Double> iterCoeff = newCoefficients.listIterator();
        ListIterator<List<String>> iterParams = newParameters.listIterator();

        while (iterCoeff.hasNext()){
            iterParams.next();
            if(iterCoeff.next() == 0) {
                iterCoeff.remove();
                iterParams.remove();
            }
        }

        coefficients = newCoefficients;
        parameters = newParameters;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < coefficients.size(); i++){
            if (i != 0 && coefficients.get(i) >= 0) {
                result.append("+");
            }
            if (coefficients.get(i) != 1.0 || (parameters.get(i).size() == 1 &&
                    parameters.get(i).get(0).equals(""))){
                result.append(coefficients.get(i));
            }
            for (int j = 0; j < parameters.get(i).size(); j++){
                if (!parameters.get(i).get(j).equals("")){
                    if (result.length() > 0) {
                        result.append("*");
                    }
                    result.append(parameters.get(i).get(j));
                }
            }
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Polynom)) {
            return false;
        }
        Polynom polynom = (Polynom) o;
        polynom.simplify();
        simplify();
        List<List<String>> firstParameters = parameters;
        List<Double> firstCoefficients = coefficients;
        List<List<String>> secondParameters = polynom.parameters;
        List<Double> secondCoefficients = polynom.coefficients;
        if (firstParameters.size() != secondParameters.size()){
            return false;
        }
        for (int i = 0; i < firstParameters.size(); i++){
            List<String> params = firstParameters.get(i);
            Collections.sort(params);
            boolean isMatch = false;
            for (int j = 0; j < secondParameters.size(); j++){
                List<String> paramsSecond = secondParameters.get(j);
                Collections.sort(paramsSecond);
                if (params.equals(paramsSecond) &&
                        firstCoefficients.get(i).equals(secondCoefficients.get(j))){
                    isMatch = true;
                    break;
                }
            }
            if (!isMatch){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coefficients, parameters);
    }

}
