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

    public Polynom add (Polynom second){
        Polynom result = new Polynom();
        simplify();
        second.simplify();
        List<List<String>> resultParameters = new ArrayList<>();
        List<Double> resultCoefficients = new ArrayList<>();
        List<List<String>> firstParameters = parameters;
        List<Double> firstCoefficients = coefficients;
        List<List<String>> secondParameters = second.parameters;
        List<Double> secondCoefficients = second.coefficients;
        for (int i = 0; i < firstParameters.size(); i++) {
            double coeff = firstCoefficients.get(i);
            List<String> params = firstParameters.get(i);
            Collections.sort(params);
            for (int j = 0; j < secondParameters.size(); j++) {
                List<String> secondParams = secondParameters.get(j);
                Collections.sort(secondParams);
                if (params.equals(secondParams)) {
                    coeff += secondCoefficients.get(j);
                }
            }

            resultCoefficients.add(coeff);
            resultParameters.add(params);
        }

        for (int i = 0; i < secondParameters.size(); i++){
            List<String> params = secondParameters.get(i);
            Collections.sort(params);
            if (! resultParameters.contains(params)) {
                resultCoefficients.add(secondCoefficients.get(i));
                resultParameters.add(secondParameters.get(i));
            }
        }
        result.coefficients = resultCoefficients;
        result.parameters = resultParameters;
        result.simplify();
        return result;
    }

    public Polynom subtract (Polynom second){
        Polynom result = new Polynom();
        simplify();
        second.simplify();
        List<List<String>> resultParameters = new ArrayList<>();
        List<Double> resultCoefficients = new ArrayList<>();
        List<List<String>> firstParameters = parameters;
        List<Double> firstCoefficients = coefficients;
        List<List<String>> secondParameters = second.parameters;
        List<Double> secondCoefficients = second.coefficients;
        for (int i = 0; i < firstParameters.size(); i++) {
            double coeff = firstCoefficients.get(i);
            List<String> params = firstParameters.get(i);
            Collections.sort(params);
            for (int j = 0; j < secondParameters.size(); j++) {
                List<String> secondParams = secondParameters.get(j);
                Collections.sort(secondParams);
                if (params.equals(secondParams)) {
                    coeff -= secondCoefficients.get(j);
                }
            }

            resultCoefficients.add(coeff);
            resultParameters.add(params);
        }

        for (int i = 0; i < secondParameters.size(); i++){
            List<String> params = secondParameters.get(i);
            Collections.sort(params);
            if (! resultParameters.contains(params)) {
                resultCoefficients.add(-secondCoefficients.get(i));
                resultParameters.add(secondParameters.get(i));
            }
        }
        result.coefficients = resultCoefficients;
        result.parameters = resultParameters;
        result.simplify();
        return result;
    }

    public Polynom multiply (Polynom second) {
        Polynom result = new Polynom();
        simplify();
        second.simplify();
        List<List<String>> resultParameters = new ArrayList<>();
        List<Double> resultCoefficients = new ArrayList<>();
        List<List<String>> firstParameters = parameters;
        List<Double> firstCoefficients = coefficients;
        List<List<String>> secondParameters = second.parameters;
        List<Double> secondCoefficients = second.coefficients;
        for (int i = 0; i < firstParameters.size(); i++) {
            for (int j = 0; j < secondParameters.size(); j++) {
                double coeff = firstCoefficients.get(i)*secondCoefficients.get(j);
                List<String> params = new ArrayList<>();
                params.addAll(firstParameters.get(i));
                params.addAll(secondParameters.get(j));
                resultCoefficients.add(coeff);
                resultParameters.add(params);
            }
        }

        result.coefficients = resultCoefficients;
        result.parameters = resultParameters;
        result.simplify();
        return result;
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
