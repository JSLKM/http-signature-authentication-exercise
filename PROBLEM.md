# Satispay Signature implementation

Satispay API gateway uses the  "http signature"  authentication type to authorise its clients.

Documentation on the http signature protocol can be found:
- on this draft: https://tools.ietf.org/html/draft-cavage-http-signatures-05 
- on Satispay's official documentation: https://s3-eu-west-1.amazonaws.com/docs.satispay.com/index.html#section/Authentication 

Target of this exercise is to replicate how clients authenticates with the Satispay gateway using RSA keys.

For this exercise you  are given:
- a pair of private and public keys of a client (`client-rsa-public-key.pub`, `client-rsa-private-key.pem`)
- a key-id: `signature-test-66289`
- the algorithm to use for the signature: `rsa-sha256`

The key-id is already authorized to call Satispay staging gateway with the provided key and algorithm.

Following the above documentation, you should be able write a program or script that calls the following url providing a signature:

`https://staging.authservices.satispay.com/wally-services/protocol/tests/signature`

and obtains the following json response:

```json
{
  "authentication_key": {
    "access_key": "signature-test-66289",
    "customer_uid": "00d25aa7-aa6e-4941-b8af-a18d4d9b8db3",
    "key_type": "RSA_PUBLIC_KEY",
    "auth_type": "SIGNATURE",
    "role": "DEVICE",
    "enable": true,
    "insert_date": "2017-11-28T15:04:50.000Z",
    "version": 1
  },
  "signature": {
    "key_id": "signature-test-66289",
    "algorithm": "RSA_SHA256",
    "headers": [
      "(request-target)"
    ],
    "signature": "EoesFDvWkF7ls97BsWqULsQP99i12AOQiW4kQLAL7gItvPt+Ma4OzLw8eibddTkYX2yOuguSTyzaYv1F//TeuX8keeNLzZBZET0EJ46YrAjZwIVd+QvqKK+Lq/fDdQR9IyCEZf6HGux3ElU/jlnxsogYc5iqPUvmqhzeyL7vaRp0OEyZySpGuWTMCuapzea7XEdOVzVBFaNOOcSKWRRm36eFQ+qmhb1V1TBC8kFF8+drpHw6T7OsdK2wN2YaeN6zHpN+Qc27hBHQ/kQYFyI9GeIf+GYSj18PfQ19y8PDIH5Y5lBpAXNwpMAltCdvq+AoNkIUv0tGvfqQsEMAFDTDwA==",
    "resign_required": false,
    "iteration_count": 2617,
    "valid": true
  },
  "signed_string": "(request-target): get /wally-services/protocol/tests/signature"
}
```
In the above response you have three pieces of information:

- **authentication_key**: define your role and credentials into the system. `authentication_key.role=PUBLIC` means that your signature doesn't work, otherwise, it works and you have a valid role assigned
- **signature**: details of your signature with headers, algorithm and signature
- **signed_string**: it's the most important piece of information because it's the signing string calculated from the server and, in order to match, you need to sign with your private key the same string

### Contraints:

- minimum header to be included: `(request-target)`, `date`, `digest`
- use java8 and add some minimal documentation on what you are doing on the main steps and some instructions on how to run the program/script.

### **Hints**

- The expected behavior of not passing any signature header is to get a 200 response with only the `role` key populated as `PUBLIC` .
- If you get a 403 error, either the signature string is malformed or the key-id is wrong
- If you get `PUBLIC` as role when providing the signature header, the key-id was recognized but the signature is wrong.
- `(request-target)` is the minimum header, start with this one and add additional headers to sign one at the time. Plus for `date` and `digest` headers
- Test that the signature works by calling the API with all verbs (GET, POST, PUT, DELETE); start with GET because it's easier
- When reading Satispay documentation, ignore references to DH exchange and activation codes; they are not needed when using RSA authentication.
- There are no constraints regarding the possibility to use external libraries. Even though, if you use few libraries, the team evaluating your exercise will be better able to see your code.