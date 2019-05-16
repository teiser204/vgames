from rest_framework import permissions

class IsGameOwnerOrReadOnly(permissions.BasePermission):
    """
    Custom permision to only allow owners of a game to edit it.
    """

    def has_object_permission(self, request, view, obj):
        # Read permissions are allowed to any request,
        # so we'll always allow GET, HEAD or OPTIONS requests.
        if request.method in permissions.SAFE_METHODS:
            return True

        # Write permissions are only allowed to the owner of the game.
        return obj.user == request.user


class IsSuperuserOrReadOnly(permissions.BasePermission):
    """
    Custom permision to only allow superusers to create or edit object
    """

    def has_permission(self, request, view):
        # Read permissions are allowed to any request,
        # so we'll always allow GET, HEAD or OPTIONS requests.
        if request.method in permissions.SAFE_METHODS:
            return True

        # Write permissions are only allowed to superusers
        return request.user.is_superuser == 1