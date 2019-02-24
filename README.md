# TSV-Filter

@author Celia Arsen
November 8, 2018
Written for Honors Intro Computer Science, Columbia University

This system is essentially a very simple relational datastream management system, made without using the streams and filters 
package in the java API. 

The system takes a tsv file as input, where the first line is header names,and the second line is data types. For example:

Name \t Age \t Cell Phone \t Zip Code \n
String \t long \t long \t long \n
Frank \t 20 \t 2121117777 \t 10027 \n
Tony \t 22 \t 2121115432 \t 10027 \n
Tony \t 18 \t 2010001123 \t 99876 \n
Ann \t 19 \t 9171118421 \t 43210 \n

The system checks to make sure the input file is properly formatted, and only streams lines to the output that are properly formatted. 
For example, a line with two tabs in a row is automatically removed from the output.

The TSVFilter uses the builder pattern, so that the user can easily add as many options as they want.
To use the system, new up a TSVFilter, and provide the name of the tsv file. The user can also choose to select which fields or values 
they should to appear in the output. 

For example:

public static void main(String[] args) {
        TSVFilter myTSVFilter = new TSVFilter
        .Builder("mydata.tsv")
        .select("Name", "Tony")
        .done();    
    System.out.println(myTSVFilter);
    new TSVPipeline(myTSVFilter).doit();
}

This code, with the data above, would provide only the second and third records in the output. 

The filter also allows a few simple terminal screen operations: allsame, count, min, max, and sum. The user provides the computation 
type and which field to compute on. Allsame returns a boolean. Allsame, count, min, and max can be applied to long and String fields. 
Sum can only be applied to long fields. 

For example:

public static void main(String[] args) {
    TSVFilter myTSVFilter = new TSVFilter
        .Builder("mydata.tsv")
        .select("Name", "Tony")
        .compute("Age", Terminal.MAX)
        .done();
    System.out.println(myTSVFilter);
    new TSVPipeline(myTSVFilter).doit();
}

This code, with the data from above, would provide the two records with the name Tony in the output, and would print 22 as the maximum
age. 

