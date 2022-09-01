clear
echo "Adding .json suffix..."
get-childitem ./ -recurse -include *.json | 
  select -expand fullname |
    foreach {
            (Get-Content $_) -replace '(")(http://fhir.org/guides/nachc/smart-on-fhir-examples/ValueSet/nachc-[^"]*)(\")', ('"' + '$2' + '.json"') |
             Set-Content $_
    }

echo "Cleaning up .json suffixes..."
get-childitem ./ -recurse -include *.json | 
  select -expand fullname |
    foreach {
            (Get-Content $_) -replace '\.json\.json', ('.json') |
             Set-Content $_
    }

echo "Updating valuesete urls..."
get-childitem ./ -recurse -include *.json | 
  select -expand fullname |
    foreach {
            (Get-Content $_) -replace 'http://fhir.org/guides/nachc/smart-on-fhir-examples/ValueSet/', ('https://nachc-cad.github.io/smart-on-fhir-examples/resources/smart-on-fhir-examples/valueset/generated/') |
             Set-Content $_
    }

echo "Updating valuesete urls..."
get-childitem ./ -recurse -include *.json | 
  select -expand fullname |
    foreach {
            (Get-Content $_) -replace 'https://nachc-cad.github.io/smart-on-fhir-examples/resources/smart-on-fhir-examples/valueset/generated/nachc', ('https://nachc-cad.github.io/smart-on-fhir-examples/resources/smart-on-fhir-examples/valueset/generated/valueset-nachc') |
             Set-Content $_
    }

echo "Updating valuesete urls..."
get-childitem ./ -recurse -include *.json | 
  select -expand fullname |
    foreach {
            (Get-Content $_) -replace 'https://nachc-cad.github.io/smart-on-fhir-examples/resources/smart-on-fhir-examples/valueset/generated/', ('https://nachc-cad.github.io/smart-on-fhir-examples/resources/smart-on-fhir-examples/vocabulary/valueset/generated/') |
             Set-Content $_
    }

echo "Done."


