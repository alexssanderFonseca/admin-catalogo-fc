package exception

import validation.ErrorDomain

class DomainException(val errorDomains:List<ErrorDomain>): SupressStackTraceException(errorDomains = errorDomains, null) {
    companion object{
        fun with(errorDomains:List<ErrorDomain>):DomainException{
            return DomainException(errorDomains)
        }
    }
}